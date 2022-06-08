package pe.joedayz.jakartaee9;

import java.io.Serializable;

public class GreetingMessage implements Serializable {

  private String message;
  public static GreetingMessage of(String message) {
    final var greetingMessage = new GreetingMessage();
    greetingMessage.setMessage(message);
    return greetingMessage;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
