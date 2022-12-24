package pe.joedayz.samples.jakartaejb.mdb;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.MessageDrivenContext;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import java.io.Serializable;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
        propertyValue = "java:/jms/queue/MyQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
        propertyValue = "jakarta.jms.Queue")
})
public class HelloMDB implements MessageListener {

  private Logger logger = Logger.getLogger(HelloMDB.class.getName());

  @Resource
  private MessageDrivenContext mdc;


  @Override
  public void onMessage(Message message) {
    logger.info(message.toString());
  }
}
