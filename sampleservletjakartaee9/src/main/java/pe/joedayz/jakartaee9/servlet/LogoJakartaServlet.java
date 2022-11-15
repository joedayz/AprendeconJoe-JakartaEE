package pe.joedayz.jakartaee9.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoJakarta", value="/logojakarta")
public class LogoJakartaServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PushBuilder pushBuilder = req.newPushBuilder();

    //if HTTP/2 is disabled (server or browser) the PushBuilder is returned null
    if(pushBuilder !=null){
      pushBuilder
          .path("images/java-hulk.jpg")
          .addHeader("content-type", "image/jpg")
          .push();
    }

    PrintWriter printWriter = resp.getWriter();
    printWriter.println("<html>");
    printWriter.println("<body>");
    printWriter.println("<img src='images/java-hulk.jpg'>");
    printWriter.println("</body>");
    printWriter.println("</html>");
  }
}
