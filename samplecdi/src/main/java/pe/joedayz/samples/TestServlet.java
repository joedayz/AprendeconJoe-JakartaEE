package pe.joedayz.samples;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/test")
public class TestServlet extends HttpServlet {

  @Inject
  private HelloWorld helloWorld;

  @Inject
  private HelloWorldSession helloWorldSession;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String type = req.getParameter("type");

    if(type==null || !type.equalsIgnoreCase("session")){
      resp.getWriter().println(helloWorld.getDate()+ " " + helloWorld.getMessage());
    }else{
      resp.getWriter().println(helloWorldSession.getDate()+ " " + helloWorldSession.getMessage());
    }
  }
}
