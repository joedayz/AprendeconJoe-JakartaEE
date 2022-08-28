package pe.joedayz.jakartaee9;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(value = "/report", asyncSupported = true)
public class ReportServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    final AsyncContext asyncContext = req.startAsync();
    asyncContext.setTimeout(90000);

    asyncContext.start(new Runnable() {
      @Override
      public void run() {
        String name = Optional.ofNullable(
            asyncContext.getRequest().getParameter("name"))
            .orElse("");
        try{
          PrintWriter printWriter = asyncContext.getResponse().getWriter();
          printWriter.println("<html>");
          printWriter.println("<body>");
          printWriter.println(generateReport(name));
          printWriter.println("</body>");
          printWriter.println("</html>");
        }catch(IOException e){
          e.printStackTrace();
        }finally{
          asyncContext.complete();
        }
      }
    });
  }

  private String generateReport(String name) {
    //Stopping thread to simulate a long-term process to generate report
    try{
      Thread.sleep(60000);
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    return new StringBuilder("<div>")
        .append("<span>")
        .append("name:")
        .append("</span>")
        .append("<span>")
        .append(name)
        .append("</span>")
        .append("</div>")
        .toString();
  }
}
