<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .card-title {
            color: #343a40;
        }
        .error {
            color: red;
            font-weight: bold;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <%
                    String errorMessage = (String) request.getAttribute("error");
                    if (errorMessage != null) {
                %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
                <%
                    } else {
                        String name = (String) request.getAttribute("name");
                        String email = (String) request.getAttribute("email");
                        String contact = (String) request.getAttribute("contact");
                        String role = (String) request.getAttribute("role");
                %>
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">Profile Details</h4>
                        <hr>
                        <p><strong>Name:</strong> <%= name %></p>
                        <p><strong>Email:</strong> <%= email %></p>
                        <p><strong>Contact:</strong> <%= contact %></p>
                        <p><strong>Role:</strong> <%= role %></p>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
