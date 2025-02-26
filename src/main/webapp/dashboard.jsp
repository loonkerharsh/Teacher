<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <style>
        body { background-color: #f4f6f9; }
        .navbar { margin-bottom: 20px; }
        .container { margin-top: 30px; }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }
        .card:hover {
            transform: scale(1.05);
        }
        .error {
            color: red;
            font-weight: bold;
            font-size: 20px;
        }
    </style>
</head>
<body>

    <!-- Include Header -->
    <%@ include file="header.jsp" %>

    <%-- Display error message if available --%>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error text-center"><%= errorMessage %></p>
    <% } %>

    <!-- Page Content -->
    <div class="container">
        <h2 class="text-center my-4">Welcome, <%=session.getAttribute("user") %>!</h2>
        <p class="text-center">Quick Stats:</p>

        <div class="row justify-content-center">
            <!-- Semesters Card -->
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Semesters</h5>
                        <p class="card-text display-4">&#128218;</p>
                        <a href="Semesters" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>

            
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
