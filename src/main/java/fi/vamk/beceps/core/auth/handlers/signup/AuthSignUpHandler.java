package fi.vamk.beceps.core.auth.handlers.signup;

import fi.vamk.beceps.common.exceptions.ConflictException;
import fi.vamk.beceps.core.auth.provider.UserDetailsDetails;
import fi.vamk.beceps.users.User;
import fi.vamk.beceps.users.UsersRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class AuthSignUpHandler {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;

  public HttpResponse<AccessRefreshToken> handle(UsernamePasswordCredentials request) {
    if (usersRepository.existsByEmail(request.getUsername())) {
      throw ConflictException.alreadyExists(User.class, String.format("email=%s", request.getUsername()));
    }

    val user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));
    usersRepository.save(user);

    val accessRefreshToken = accessRefreshTokenGenerator.generate(new UserDetailsDetails(user));

    if (accessRefreshToken.isPresent()) {
      return HttpResponse.ok(accessRefreshToken.get());
    }
    return HttpResponse.serverError();
  }
}
