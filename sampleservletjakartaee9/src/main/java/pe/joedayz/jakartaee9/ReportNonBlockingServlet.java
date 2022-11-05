package pe.joedayz.jakartaee9;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet(value="/nonBlockingReport", asyncSupported = true)
public class ReportNonBlockingServlet extends HttpServlet {

  private static Logger logger = Logger.getLogger(ReportNonBlockingServlet.class.getName());

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    final AsyncContext asyncContext = req.startAsync();

    //Listener to InputStream
    final ServletInputStream inputStream = req.getInputStream();
    inputStream.setReadListener(new ReadListener() {

      byte buffer[] = new byte[4 * 1024];
      StringBuilder data = new StringBuilder();

      @Override
      public void onDataAvailable() throws IOException {
        try {
          int length = inputStream.read(buffer);
          data.append(new String(buffer, 0, length));
        }catch (IOException ex){
          logger.severe(ex.getMessage());
        }
      }

      @Override
      public void onAllDataRead() throws IOException {
        logger.info(data.toString());
      }

      @Override
      public void onError(Throwable throwable) {

      }
    });

    //Listener to OutputStream
    ServletOutputStream outputStream = resp.getOutputStream();
    outputStream.setWriteListener(new WriteListener() {
      @Override
      public void onWritePossible() throws IOException {
        String name = Optional.
            ofNullable(asyncContext.getRequest()
                .getParameter("name")).orElse("");
        List<String> datas = new ArrayList<>();
        datas.add("<html>\n");
        datas.add("<body>\n");
        datas.add(generateReport(name.toString()));
        datas.add("\n</body>\n");
        datas.add("</html>\n");
        while(outputStream.isReady() && !datas.isEmpty()){
          outputStream.println(datas.remove(0));
        }
        asyncContext.complete();
      }

      @Override
      public void onError(Throwable throwable) {

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
