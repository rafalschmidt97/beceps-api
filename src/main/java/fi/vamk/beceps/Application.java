package fi.vamk.beceps;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Beceps",
        version = "1.0",
        description = "Gym tool for scheduling workouts.",
        contact = @Contact(
            name = "Rafał Schmidt",
            url = "https://rafalschmidt.com",
            email = "rafalschmidt97@gmail.com"
        )
    )
)
public class Application {
  public static void main(String[] args) {
    Micronaut.run(Application.class);
  }
}
