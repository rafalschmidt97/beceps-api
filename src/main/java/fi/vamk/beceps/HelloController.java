package fi.vamk.beceps;

import fi.vamk.beceps.common.exceptions.BadRequestException;
import fi.vamk.beceps.common.exceptions.NotFoundException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Controller
@RequiredArgsConstructor
public class HelloController {
  private final HelloRepository helloRepository;

  @Get
  @Produces(MediaType.TEXT_PLAIN)
  public String index() {
    return "Hello World";
  }

  @Get("/hello/{message}")
  public Hello find(String message) {
    return helloRepository
      .findByMessage(message)
      .orElseThrow(() -> new NotFoundException(Hello.class, message));
  }

  @Post("/hello/{message}")
  public HttpResponse add(String message) {
    val record = new Hello(message);
    helloRepository.save(record);
    return HttpResponse.status(HttpStatus.CREATED);
  }

  @Get("/error/custom")
  public String customError() {
    throw new BadRequestException("Try to catch me!");
  }
}
