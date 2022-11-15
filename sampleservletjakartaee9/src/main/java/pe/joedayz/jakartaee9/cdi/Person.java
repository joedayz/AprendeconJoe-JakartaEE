package pe.joedayz.jakartaee9.cdi;

import jakarta.annotation.PostConstruct;
import java.util.logging.Logger;

public class Person {

  private static Logger LOGGER = Logger.getLogger(Person.class.getName());

  private String name;

  @PostConstruct
  public void initPerson(){
    LOGGER.info("Executed.");
  }
}
