package pe.joedayz.jakartaee9.servlet;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class GreetingService {

  public GreetingMessage buildGreetingMessage(String name) {
    return GreetingMessage.of("Say Hello to " + name + " at " + LocalDateTime.now());
  }
}
