package pe.joedayz.samples.jakartaejb.schedule;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.ScheduleExpression;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerService;
import jakarta.inject.Singleton;
import java.util.logging.Logger;

@Singleton
@Startup
public class HelloProgrammaticallySchedule {

  private Logger logger = Logger.getLogger(HelloProgrammaticallySchedule.class.getName());

  @Resource
  private TimerService timerService;

  @PostConstruct
  public void init(){
    ScheduleExpression expression = new ScheduleExpression();
    expression.hour("*");
    expression.minute("0,10,20,30,40,50");

    timerService.createCalendarTimer(expression);
  }

  @Timeout
  public void execute(Timer timer){
    logger.info("Hello programatico");
  }
}
