package FirstProject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
			String s = request.getParameter("uname");
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=blueviolet text=brown><strong><h2 style='text-align:center; text-decoration:underline'>");
			pw.println("Welcome:"+s);
			pw.println("</h2></strong></body></html>");
			
	}

}
