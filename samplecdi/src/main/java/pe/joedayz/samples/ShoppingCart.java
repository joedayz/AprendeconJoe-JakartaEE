package pe.joedayz.samples;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

@ConversationScoped
public class ShoppingCart implements Serializable {

  @Inject
  private Conversation conversation;

  private Long itemNumber;

  @PostConstruct
  public void init(){
    this.itemNumber = 0L;
  }

  public void increaseItemNumber(){
    this.itemNumber++;
  }

  public Long getItemNumber() {
    return itemNumber;
  }

  public void startConversation(){
    if(conversation.isTransient()){
      conversation.begin();
    }
  }

  public void endConversation(){
    if(!conversation.isTransient()){
      conversation.end();
    }
  }

  public String getConversationId(){
    return conversation.getId();
  }
}
