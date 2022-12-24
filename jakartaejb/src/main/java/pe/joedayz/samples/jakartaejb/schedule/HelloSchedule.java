package pe.joedayz.samples.jakartaejb.schedule;

import jakarta.ejb.Schedule;
import jakarta.inject.Singleton;
import java.util.logging.Logger;

@Singleton
public class HelloSchedule {

  private Logger logger = Logger.getLogger(HelloSchedule.class.getName());

  @Schedule(hour = "*",minute = "0,10,20,30,40,50")
  public void hello(){
    logger.info("Hello :)");
  }
}
