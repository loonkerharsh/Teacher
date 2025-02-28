package mypkg;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class DeleteSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private PreparedStatement psd;

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc", "root", "1234");
            psd = cn.prepareStatement("DELETE FROM subjects WHERE subject_name=? AND teacher_id=? AND semester=?");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectName = request.getParameter("subjectName");
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int semester = Integer.parseInt(request.getParameter("semester"));

        try {
            psd.setString(1, subjectName);
            psd.setInt(2, teacherId);
            psd.setInt(3, semester);
            psd.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (psd != null) psd.close();
            if (cn != null) cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
