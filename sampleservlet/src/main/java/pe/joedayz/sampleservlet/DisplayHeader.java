package pe.joedayz.sampleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayInHeaderServlet")
public class DisplayHeader extends HttpServlet {

  private static final long serialVersionUID = 1l;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    handleRequest(req, resp);
  }

  private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    //set response content type
    resp.setContentType("text/html");

    //print the response
    PrintWriter out = resp.getWriter();
    String title = "HTTP Header Request Example";
    String docType = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
    out.println(docType +
        "<html>\n" +
        "<head><title>" + title + "</title></head>\n"+
        "<body bgcolor = \"#f0f0f0\">\n" +
        "<h1 align = \"center\">" + title + "</h1>\n" +
        "<table width = \"100px\" border = \"1\" align = \"center\">\n" +
        "<tr bgcolor = \"#949494\">\n" +
        "<th>Header Name</th><th>Header Value(s)</th>\n"+
        "</tr>\n");

    Enumeration<String> headerNames = req.getHeaderNames();
    while(headerNames.hasMoreElements()){
      String paramName = headerNames.nextElement();
      out.print("<tr><td>" + paramName + "</td>\n");
      String paramValue = req.getHeader(paramName);
      out.println("<td> " + paramValue + "</td></tr>\n");
    }
    out.println("</table>\n</body></html>");
  }
}
