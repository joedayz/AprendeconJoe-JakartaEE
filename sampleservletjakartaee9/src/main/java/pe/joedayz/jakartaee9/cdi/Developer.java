package pe.joedayz.jakartaee9.cdi;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.logging.Logger;

//JAKARTA CONTEXT AND DEPENDENCY INJECTION
@ApplicationScoped
public class Developer extends Person{

  private static Logger LOGGER = Logger.getLogger(Developer.class.getName());
  /*
  @RequestScoped
  @SessionScoped
  @ApplicationScoped
  @Dependent
  @ConversationScoped
   */
  @Inject
  private Brain brain;

  @PostConstruct
  public void initDeveloper(){
    LOGGER.info("Executed");
  }

//  public Developer(Brain brain) {
//    this.brain = brain;
//  }

  public void solveProblem(Object problem){
      //Logic before
      Object solve = brain.reason(problem);
      //Logic after
  }

  @PreDestroy
  public void prepareToDestroy(){
    LOGGER.info("Preparing to destroy processing...");
  }
}
