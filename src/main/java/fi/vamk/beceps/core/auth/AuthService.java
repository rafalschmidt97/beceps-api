package fi.vamk.beceps.core.auth;

import fi.vamk.beceps.accounts.Account;
import fi.vamk.beceps.accounts.AccountsRepository;
import fi.vamk.beceps.common.exceptions.ConflictException;
import fi.vamk.beceps.common.exceptions.UnauthorizedException;
import fi.vamk.beceps.core.auth.refresh.RefreshToken;
import fi.vamk.beceps.core.auth.refresh.RefreshTokensRepository;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AuthService {
  private final AccountsRepository accountsRepository;
  private final RefreshTokensRepository refreshTokensRepository;
  private final PasswordEncoder passwordEncoder;

  void signUp(String email, String password) {
    if (accountsRepository.existsByEmail(email)) {
      throw ConflictException.alreadyExists(Account.class, String.format("email=%s", email));
    }

    val account = new Account(email, passwordEncoder.encode(password));
    accountsRepository.save(account);
  }

  public String generateAndSaveRefreshToken(Long accountId) {
    val randomText = generateRefreshToken();
    val twoYearsInFutureDate = getTwoYearsLaterDate();
    val token = new RefreshToken(twoYearsInFutureDate, randomText, accountId);
    refreshTokensRepository.save(token);
    return randomText;
  }

  void logout(String refreshToken) {
    refreshTokensRepository.deleteByToken(refreshToken);
  }

  void logoutAllDevices(Long accountId) {
    refreshTokensRepository.deleteAllByAccountId(accountId);
  }

  void checkRefreshToken(String refreshToken) {
    val token = refreshTokensRepository.findByToken(refreshToken)
        .orElseThrow(() -> new UnauthorizedException("Refresh token does not exists."));

    if (token.isExpired()) {
      throw new UnauthorizedException("Refresh token has expired.");
    }
  }

  private String generateRefreshToken() {
    val randomText = UUID.randomUUID().toString();
    val encodedText = Base64.getEncoder().encode(randomText.getBytes(StandardCharsets.UTF_8));
    return new String(encodedText, StandardCharsets.UTF_8);
  }

  private Date getTwoYearsLaterDate() {
    val calendar = Calendar.getInstance();
    calendar.add(Calendar.YEAR, 2);
    return calendar.getTime();
  }
}
