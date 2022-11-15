package pe.joedayz.jakartaee9.cdi;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;

@ApplicationScoped
public class AnimalMain {

  @Inject
  @Dog
  private Animal dog;

  @Inject
  @Cat
  private Animal cat;

  @Inject
  private Animal commonDog;

  /*
  JMS QUEUE, DATA SOURCES, u OTROS
   */
  @Resource(lookup = "java:MyDatasource")
  DataSource dataSource;
}
