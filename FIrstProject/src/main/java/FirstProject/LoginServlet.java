package FirstProject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn;
 
    public void init() throws ServletException {
        try {
        	ServletContext sc= getServletContext();
        	String s1= sc.getInitParameter("driver");
        	String s2= sc.getInitParameter("url");
        	String s3= sc.getInitParameter("username");
        	String s4= sc.getInitParameter("password");
            Class.forName(s1);
            conn = DriverManager.getConnection(s2,s3,s4);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String s1 = request.getParameter("uname");
            String s2 = request.getParameter("pword");
            PreparedStatement pstmt = conn.prepareStatement("select * from uinfo where uname=? and pword=?");
            pstmt.setString(1, s1);
            pstmt.setString(2, s2);
            ResultSet rs = pstmt.executeQuery();
            PrintWriter pw = response.getWriter();
           
            if (rs.next()) {
                RequestDispatcher rd=request.getRequestDispatcher("/welcome");
                rd.forward(request, response);
            } else {
                pw.println("invalid username or password");
                RequestDispatcher rd= request.getRequestDispatcher("/login.html");
                rd.include(request, response);
            }
       
        } catch (SQLException e) {
            e.printStackTrace();
		}
	}

}
