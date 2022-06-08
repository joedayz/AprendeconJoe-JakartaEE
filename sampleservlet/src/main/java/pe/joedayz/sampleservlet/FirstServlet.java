package pe.joedayz.sampleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet1")
public class FirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    handleRequest(req, resp);
  }

  private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    String userName = req.getParameter("userName");
    out.print("Welcome " + userName);

    HttpSession session = req.getSession();
    session.setAttribute("uname", userName);

    out.print("<a href='servlet2'>go</a>");
    out.close();
  }
}
