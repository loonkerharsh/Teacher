package mypkg;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class verifyy
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection cn;
	private PreparedStatement psq,psu;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc","root","1234");
			psq=cn.prepareStatement("Select id,name,password from teachers where email=?");
			
						
		}
		catch(ClassNotFoundException e) {
			System.out.println("driver can't load");
		}
		catch(SQLException e) {
			System.out.println("Sql Alert");
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			psq.close();
			psu.close();
			cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String l_id = request.getParameter("username");
		String pass = request.getParameter("password");	
		try {
			psq.setString(1,l_id);
			ResultSet rs = psq.executeQuery();
			if(rs.next())
			{
				String passwd;
				passwd=rs.getString("password");
					
				if(pass.equals(passwd)) 
				{
					HttpSession s=request.getSession();
					s.setAttribute("user", rs.getString("name"));
					s.setAttribute("t_id", rs.getInt("id"));
					response.sendRedirect("dashboard.jsp");
				}
				else
				{
					request.setAttribute("errorMessage", "Invalid Username or Password!!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
			else
			{
				String html = "<html>"
						+ "<body bgcolor='red'>"
						+ "<center><h1> oops!! Login Failed</h1>"
						+ "<a href=login.jsp>Login Again</a>"
						+ "</center>"
						+ "</body>"
						+ "</html>";
				        response.getWriter().print(html);
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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