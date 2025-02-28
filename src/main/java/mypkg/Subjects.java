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

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc","root","1234");
            psq = cn.prepareStatement("SELECT * FROM subjects WHERE teacher_id=? AND semester=?");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver can't load");
        }
        catch (SQLException e) {
            System.out.println("SQL Alert: " + e.getMessage());
        }
    }

    public Subjects() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession();
        if (ssn.getAttribute("t_id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int t_id = (int) ssn.getAttribute("t_id");  // Get t_id from Session
        int sem = Integer.parseInt(request.getParameter("sem"));
        
        System.out.println("Teacher ID: " + t_id);
        System.out.println("Semester: " + sem);

        List<String[]> subjects = new ArrayList<>();
        
        try {
            psq.setInt(1, t_id);
            psq.setInt(2, sem);
            ResultSet rs = psq.executeQuery();
            
            while (rs.next()) {
                String[] subject = new String[6];
                subject[0] = rs.getString("subject_name");  // Subject Name
                subject[1] = rs.getString("completion_note");  // Completion Note
                subject[2] = rs.getString("classroom");  // Classroom
                subject[3] = rs.getString("class_time");  // Timing
                subject[4] = String.valueOf(rs.getInt("total_students"));  // Total Students
                subject[5] = String.valueOf(rs.getInt("id"));
                subjects.add(subject);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Set Attributes and Forward
        request.setAttribute("subjectsList", subjects);
        request.setAttribute("semester", sem);  // Send Semester Information
        request.getRequestDispatcher("subjects.jsp").forward(request, response);
    }
}
