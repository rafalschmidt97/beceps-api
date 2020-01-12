package fi.vamk.beceps;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Controller()
@Slf4j
public class HelloController {
  @Get
  @Produces(MediaType.TEXT_PLAIN)
  public String index() {
    val text = "Hello World request";
    log.info(text);
    return "Hello World";
  }
}
