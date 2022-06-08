package pe.joedayz.sampleservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DNS Servlet", urlPatterns = "/dns/*")
public class DNSServlet extends HttpServlet {

  private ConcurrentMap<String, String>  ipMap;

  @Override
  public void init() throws ServletException {
    ipMap = new ConcurrentHashMap<>();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String domainName =  req.getParameter("domainName");
    String ip = req.getParameter("ip");

    if(domainName==null || ip==null){
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    ipMap.put(domainName, ip);
    resp.setStatus(HttpServletResponse.SC_CREATED);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String domainName = req.getParameter("domainName");

    if(domainName==null){
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    PrintWriter printWriter = resp.getWriter();
    printWriter.println(Optional.ofNullable(ipMap.get(domainName))
        .orElse(""));
  }
}
