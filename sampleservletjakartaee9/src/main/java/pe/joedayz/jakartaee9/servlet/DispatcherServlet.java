package pe.joedayz.jakartaee9.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/helloworld.html")
public class DispatcherServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String type = req.getParameter("type");

    PrintWriter writer = resp.getWriter();
    writer.println("<html>");
    writer.println("<head>");
    writer.println("</head>");
    writer.println("<body>");

    RequestDispatcher dispatcher = req.getRequestDispatcher("/helloworld");

    if("forward".equals(type)){
      dispatcher.forward(req,resp);
    }else{
      dispatcher.include(req,resp);
    }

    writer.println("</body>");
    writer.println("</html>");
  }
}
