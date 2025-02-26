<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Semesters</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        
        .container {
            margin-top: 30px;
        }
    </style>
</head>
<body>
    

    <div class="container">
        <h2 class="text-center">Semester List</h2>

        <table class="table table-bordered table-striped mt-4">
            <thead>
                <tr>
                    <th>Semester</th>
                </tr>
            </thead>
            <tbody>
            
                <%
             	    Integer t_id = (Integer) session.getAttribute("t_id");
                    java.util.List<Integer> semesters = (java.util.List<Integer>) request.getAttribute("semesters");
                    if (semesters == null || semesters.isEmpty()) {
                %>
                        <tr>
                            <td colspan="1" class="text-center">No current semester</td>
                        </tr>
                <%
                    } else {
                        for (Integer sem : semesters) {
                %>
                            <tr>
                                <td>
                                    <a href="Subjects?sem=<%= sem %>&t_id=<%= t_id %>">
                                        Semester <%= sem %>
                                    </a>
                                    
                                  
                                </td>
                            </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS (Optional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
