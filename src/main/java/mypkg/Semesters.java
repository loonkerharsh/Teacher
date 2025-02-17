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
 * Servlet implementation class Semesters
 */
public class Semesters extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection cn;
	private PreparedStatement psq;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc","root","1234");
			psq=cn.prepareStatement("Select distinct semester from subjects where teacher_id=?");
			
						
		}
		catch(ClassNotFoundException e) {
			System.out.println("driver can't load");
		}
		catch(SQLException e) {
			System.out.println("Sql Alert");
		}
	}

	
    public Semesters() {
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
		else {
		
			String html="<html><head><meta charset=UTF-8><title>Dashboard</title><style>"
						+ "body { font-family: Arial, sans-serif; }"
						+".navbar { background: #333; padding: 10px; display: flex; justify-content: space-between; border-radius:8px;}"
						+".nav-links { display: flex; }"
						+".navbar a { color: white; margin: 10px; text-decoration: none; padding: 5px;}"
						+".navbar a:hover {background: #555; border-radius:8px;}"
						+".container {padding: 25px;}"
						+"table{padding:60px;}"
						+"</style>"
						+"</head>"
						+"<body>"
						+"<div class='navbar'>"
						+"<div class=nav-links>"
			            +"<a href=dashboard.jsp>Home</a>"
			            +"<a href=semesters.jsp>Semesters</a>"
			            +"<a href=profile.jsp>Profile</a>"
			            +"</div>"
			            +"<a href=Logout>Logout</a>"
			            +"</div><center>Semester List<table cellpadding='10px' cellspacing='5px'>";
		
		
		
			int n = Integer.parseInt(ssn.getAttribute("t_id").toString());
		
			try {
			    psq.setInt(1, n);
			    ResultSet rs = psq.executeQuery();

			    if (!rs.isBeforeFirst()) 
			    {  
			        html += "<li>No current semester</li>";
			    } 
			    else 
			    {
			        while (rs.next())
			        {
			        	int sem= rs.getInt("semester");
			            html += "<tr><td><a href='Subjects?sem="+sem+"&t_id="+n+"'>" +sem + "</a></td></tr>";
			        }
			    }
			} 
			catch (SQLException e) 
			{
			    html += "<li>Error fetching data</li>";
			    e.printStackTrace();
			}

			html+="</table></center></body></html>";		
			response.getWriter().println(html);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
