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
import java.util.ArrayList;

public class Semesters extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private PreparedStatement psq;

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmc","root","1234");
            psq=cn.prepareStatement("Select distinct semester from subjects where teacher_id=?");
        }
        catch(ClassNotFoundException e) {
            System.out.println("Driver can't load");
        }
        catch(SQLException e) {
            System.out.println("SQL Alert");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession();
        if(ssn.getAttribute("t_id") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int teacherId = Integer.parseInt(ssn.getAttribute("t_id").toString());
        ArrayList<Integer> semesterList = new ArrayList<>();

        try {
            psq.setInt(1, teacherId);
            ResultSet rs = psq.executeQuery();

            while (rs.next()) {
                semesterList.add(rs.getInt("semester"));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("semesters", semesterList);
        request.getRequestDispatcher("semesters.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
