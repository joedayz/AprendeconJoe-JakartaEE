package pe.joedayz.samples.jakartaejb.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Startup;
import jakarta.inject.Singleton;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class AccessSingleton {

  private Integer count;

  @PostConstruct
  public void init(){
    this.count=0;
  }


  @Lock(LockType.WRITE)
  public void increment(){
    this.count++;
  }

  @Lock(LockType.READ)
  public Integer getCount(){
    return this.count;
  }
}
