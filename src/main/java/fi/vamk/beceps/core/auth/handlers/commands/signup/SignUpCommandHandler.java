package fi.vamk.beceps.core.auth.handlers.commands.signup;

import fi.vamk.beceps.common.bus.command.CommandHandler;
import fi.vamk.beceps.common.exceptions.ConflictException;
import fi.vamk.beceps.core.auth.api.events.commands.signup.SignUpCommand;
import fi.vamk.beceps.core.auth.infrastructure.provider.DatabaseUserDetails;
import fi.vamk.beceps.users.domain.User;
import fi.vamk.beceps.users.infrastructure.persistence.UsersRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Singleton
@RequiredArgsConstructor
public class SignUpCommandHandler implements CommandHandler<HttpResponse<AccessRefreshToken>, SignUpCommand> {
  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;
  private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;

  @Override
  public HttpResponse<AccessRefreshToken> handle(SignUpCommand command) {
    if (usersRepository.existsByEmail(command.getEmail())) {
      throw ConflictException.alreadyExists(User.class, String.format("email=%s", command.getEmail()));
    }

    val user = new User(command.getEmail(), passwordEncoder.encode(command.getPassword()));
    usersRepository.insert(user);

    val accessRefreshToken = accessRefreshTokenGenerator.generate(new DatabaseUserDetails(user));

    if (accessRefreshToken.isPresent()) {
      return HttpResponse.ok(accessRefreshToken.get());
    }
    return HttpResponse.serverError();
  }
}
