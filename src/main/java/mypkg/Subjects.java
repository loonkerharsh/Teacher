package mypkg;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public Subjects() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession ssn = request.getSession();
        if (ssn.getAttribute("t_id") == null) 
        {
            response.sendRedirect("login.jsp");
            return;
        }
        int t_id = Integer.parseInt(request.getParameter("t_id"));
        int sem = Integer.parseInt(request.getParameter("sem"));
        
        List<String[]> subjects = new ArrayList<>();
        
        try
        {
            psq.setInt(1, t_id);
            psq.setInt(2, sem);
            ResultSet rs = psq.executeQuery();
            
            while(rs.next())
            {
                String[] subject = new String[5];
                subject[0] = rs.getString(3);  // Subject Name
                subject[1] = rs.getString(7);  // Completion Note
                subject[2] = rs.getString(8);  // Classroom
                subject[3] = rs.getString(6);  // Timing
                subject[4] = String.valueOf(rs.getInt(5)); // Total Students
                subjects.add(subject);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        request.setAttribute("subjectsList", subjects);
        request.setAttribute("semester", sem);
        request.getRequestDispatcher("subjects.jsp").forward(request, response);
    }
}
