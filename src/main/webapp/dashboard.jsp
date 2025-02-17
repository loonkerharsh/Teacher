 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp"); // Redirect to login page
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
  <style>
        body { font-family: Arial, sans-serif; }
        .navbar { background: #333; padding: 10px; display: flex; justify-content: space-between; border-radius:8px;}
        .nav-links { display: flex; }
        .navbar a { color: white; margin: 10px; text-decoration: none; padding: 5px;}
        .navbar a:hover {background: #555; color:; border-radius:8px;}
        .container {padding: 25px;}
         .error {
            color: red;
            font-weight: bold;
            font-size:20px;
        }
    </style>
</head>
<body>
  	 <%-- Display error message if available --%>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error"><%= errorMessage %></p>
    <% } %>
    
    <div class="navbar">
        <div class="nav-links">
            <a href="dashboard.jsp">Home</a>
            <a href="Semesters">Semesters</a>
            <a href="ProfileServlet">Profile</a>
        </div>
        <a href="Logout">Logout</a>
    </div>
    
    <div class="container">
        <h2>Welcome, <%=session.getAttribute("user") %> </h2>
        <p>Quick Stats:</p>
        <ul>
            <li>Semesters: [X]</li>
            <li>Subjects: [Y]</li>
            <li>Total Students: [Z]</li>
        </ul>
    </div>

</body>
</html>