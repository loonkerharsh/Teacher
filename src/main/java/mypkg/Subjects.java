package mypkg;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Subjects
 */
public class Subjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection cn;
	private PreparedStatement psq;
   
	
	public void init(ServletConfig config) throws ServletException 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc","root","1234");
			psq=cn.prepareStatement("select * from subjects where teacher_id=? and semester=?");
			
						
		}
		catch(ClassNotFoundException e) {
			System.out.println("driver can't load");
		}
		catch(SQLException e) {
			System.out.println("Sql Alert");
		}
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Subjects() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession ssn=request.getSession();
		if(ssn.getAttribute("t_id")==null) 
		{
			response.sendRedirect("login.jsp");
			return ;
		}
		int t_id=Integer.parseInt(request.getParameter("t_id").toString());
		int sem=Integer.parseInt(request.getParameter("sem").toString());
		
		String html="<html><title>Dashboard</title>\r\n"
				+ "  <style>\r\n"
				+ "        body { font-family: Arial, sans-serif; }\r\n"
				+ "        .navbar { background: #333; padding: 10px; display: flex; justify-content: space-between; border-radius:8px;}\r\n"
				+ "        .nav-links { display: flex; }\r\n"
				+ "        .navbar a { color: white; margin: 10px; text-decoration: none; padding: 5px;}\r\n"
				+ "        .navbar a:hover {background: #555; color:; border-radius:8px;}\r\n"
				+ "        .container {padding: 25px;}\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "  \r\n"
				+ "\r\n"
				+ "    <div class=\"navbar\">\r\n"
				+ "        <div class=\"nav-links\">\r\n"
				+ "            <a href=\"dashboard.jsp\">Home</a>\r\n"
				+ "            <a href=\"Semesters\">Semesters</a>\r\n"
				+ "            <a href=\"profile.jsp\">Profile</a>\r\n"
				+ "        </div>\r\n"
				+ "        <a href=\"Logout\">Logout</a>\r\n"
				+ "    </div><table><tr><td>Subject</td><td>Completion Note</td><td>Classroom</td><td>timing</td><td>Total Students</td></tr>";
		
		try
		{
			psq.setInt(1, t_id);
			psq.setInt(2, sem);
			
			ResultSet rs=psq.executeQuery();
			
			while(rs.next())
			{
				html+="<tr><td>"+rs.getString(3)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getInt(5)+"</td></tr>";
			}
		}
		catch(SQLException e)
		{
			
		}
		html+="</table></body></html>";
		response.getWriter().println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
