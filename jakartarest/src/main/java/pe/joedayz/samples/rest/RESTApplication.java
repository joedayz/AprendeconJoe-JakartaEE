package pe.joedayz.samples.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
public class RESTApplication extends Application {

//  @Override
//  public Set<Class<?>> getClasses(){
//    Set<Class<?>> classes = new HashSet<>();
//    classes.add(UserResource.class);
//    return classes;
//  }
}
