<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Subjects</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <style>
        body { background-color: #f4f6f9; }
        .card { margin-bottom: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }
        .navbar { margin-bottom: 20px; }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Teacher Portal</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="dashboard.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="Semesters">Semesters</a></li>
                    <li class="nav-item"><a class="nav-link" href="ProfileServlet">Profile</a></li>
                    <li class="nav-item"><a class="nav-link" href="Logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Page Content -->
    <div class="container">
        <h2 class="text-center my-4">Subjects for Semester ${semester}</h2>
        <div class="row">
            <%
                List<String[]> subjects = (List<String[]>) request.getAttribute("subjectsList");
                if (subjects.isEmpty()) {
            %>
                <p class="text-center">No subjects found for this semester.</p>
            <%
                } else {
                    for (String[] subject : subjects) {
            %>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= subject[0] %></h5>
                        <p class="card-text"><strong>Completion Note:</strong> <%= subject[1] %></p>
                        <p class="card-text"><strong>Classroom:</strong> <%= subject[2] %></p>
                        <p class="card-text"><strong>Timing:</strong> <%= subject[3] %></p>
                        <p class="card-text"><strong>Total Students:</strong> <%= subject[4] %></p>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
