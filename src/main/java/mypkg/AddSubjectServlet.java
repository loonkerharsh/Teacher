package mypkg;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class AddSubjectServlet
 */
public class AddSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	int teacherId = Integer.parseInt(request.getParameter("teacherId"));
            String subjectName = request.getParameter("subjectName");
            String semester = request.getParameter("semester");
            int totalStudents = Integer.parseInt(request.getParameter("totalStudents"));
            String classTime = request.getParameter("classTime");
            String completionNote = request.getParameter("completionNote");
            String classroom = request.getParameter("classroom");

            // Database Insertion Logic
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc", "root", "1234");
                String sql = "INSERT INTO subjects (teacher_id, subject_name, semester, total_students, class_time, completion_note, classroom) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, teacherId);
                stmt.setString(2, subjectName);
                stmt.setString(3, semester);
                stmt.setInt(4, totalStudents);
                stmt.setString(5, classTime);
                stmt.setString(6, completionNote);
                stmt.setString(7, classroom);
                stmt.executeUpdate();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Redirect back to Subjects Page
            response.sendRedirect("Subjects?sem="+semester+"&t_id="+teacherId);
        }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
