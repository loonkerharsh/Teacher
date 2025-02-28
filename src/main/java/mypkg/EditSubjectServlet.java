package mypkg;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class EditSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private PreparedStatement psu;

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc", "root", "1234");
            psu = cn.prepareStatement("UPDATE subjects SET completion_note=?, class_time=?, total_students=? WHERE id=? AND teacher_id=? AND semester=?");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectName = request.getParameter("subjectId");
        String completionNote = request.getParameter("completionNote");
        String classTime = request.getParameter("classTime");
        int totalStudents = Integer.parseInt(request.getParameter("totalStudents"));
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int semester = Integer.parseInt(request.getParameter("semester"));

        try {
            psu.setString(1, completionNote);
            psu.setString(2, classTime);
            psu.setInt(3, totalStudents);
            psu.setString(4, subjectName);
            psu.setInt(5, teacherId);
            psu.setInt(6, semester);
            int rowsAffected = psu.executeUpdate();
            if (rowsAffected > 0) {
                response.sendRedirect("Subjects?sem="+semester);
            } else {
                System.out.println("Update failed. No rows were affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (psu != null) psu.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
