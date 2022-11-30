package pe.joedayz.samples.restclient;

import java.util.logging.Logger;

public class EmailService {

  private static final Logger logger = Logger.getLogger(EmailService.class.getName());

  public Boolean send(String from, String to, String subject, String message){
    logger.info(String.format("Email:from %s to %s with a subject %s and message %s",
        from, to, subject, message));
    return true;
  }
}
