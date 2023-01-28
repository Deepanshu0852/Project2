package FirstProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String s1 = request.getParameter("fname");
			String s2 = request.getParameter("lname");
			String s3 = request.getParameter("uname");
			String s4 = request.getParameter("pword");
			PreparedStatement pstmt = conn.prepareStatement("insert into uinfo values(?,?,?,?)");
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			pstmt.setString(3, s3);
			pstmt.setString(4, s4);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter pw = response.getWriter();
		pw.println("<html><body bgcolor=green text=yellow><center>");
		pw.println("<h1>You Have Registered Successfully</h1>");
		pw.println("<a href=login.html>Login</a>");
		pw.println("</center></body></html>");
	}
	}
	


