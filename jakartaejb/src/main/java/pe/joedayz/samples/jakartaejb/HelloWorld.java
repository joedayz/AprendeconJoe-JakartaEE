package pe.joedayz.samples.jakartaejb;

import jakarta.ejb.Stateless;

@Stateless
public class HelloWorld {

  public String sayHello(){
    return "Hello World";
  }
}
