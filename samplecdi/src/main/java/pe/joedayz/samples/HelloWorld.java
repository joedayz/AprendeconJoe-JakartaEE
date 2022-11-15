package pe.joedayz.samples;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import java.time.LocalDateTime;

@RequestScoped
public class HelloWorld {

  private String message;
  private LocalDateTime date;

  @PostConstruct
  public void init(){
    this.message = "Hello World";
    this.date = LocalDateTime.now();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
