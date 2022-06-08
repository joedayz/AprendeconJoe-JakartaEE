package pe.joedayz.sampleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet2")
public class SecondServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    handleRequest(req, resp);
  }

  private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    HttpSession session = req.getSession(false);
    String uname = (String) session.getAttribute("uname");

    out.print("Hello " + uname);
    out.close();
  }
}
