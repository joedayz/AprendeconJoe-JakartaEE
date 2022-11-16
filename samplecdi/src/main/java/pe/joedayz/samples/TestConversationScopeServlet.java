package pe.joedayz.samples;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testconversationscope")
public class TestConversationScopeServlet extends HttpServlet {

  @Inject
  private ShoppingCart shoppingCart;

  private final String templateHTML = "<html><body><span>%d</span></body></html>";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.getWriter().println(String.format(templateHTML, shoppingCart.getItemNumber()));
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String action = req.getParameter("action");

    if("startConversation".equalsIgnoreCase(action)){
      shoppingCart.startConversation();
    }
    if("endConversation".equalsIgnoreCase(action)){
      shoppingCart.endConversation();
    }else{
      shoppingCart.increaseItemNumber();
    }

    String cid = shoppingCart.getConversationId()==null? "" : shoppingCart.getConversationId();
    resp.addHeader("cid", cid);


  }
}
