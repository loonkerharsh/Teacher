<%@page import="com.mysql.cj.Session"%>
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

                        
                        <!-- Edit Button -->
						<button class="btn btn-warning" data-bs-toggle="modal" 
    						data-bs-target="#editSubjectModal" 
						    onclick="populateEditForm('<%= subject[0] %>', '<%= subject[1] %>', '<%= subject[2] %>', '<%= subject[3] %>', '<%= subject[4] %>', '<%= subject[5] %>')">
    						Edit
						</button>

                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>

    <!-- Add Subject Button -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addSubjectModal">
        Add Subject
    </button>

    <!-- Add Subject Modal -->
    <div class="modal fade" id="addSubjectModal" tabindex="-1" aria-labelledby="addSubjectModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addSubjectModalLabel">Add New Subject</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <form action="AddSubjectServlet" method="post">
            <div class="modal-body">
              <input type="hidden" name="teacherId" value="<%=session.getAttribute("t_id")%>">
              <div class="mb-3">
                <label for="subjectName" class="form-label">Subject Name</label>
                <input type="text" class="form-control" id="subjectName" name="subjectName" required>
              </div>
              <div class="mb-3">
                <label for="semester" class="form-label">Semester</label>
                <input type="text" class="form-control" id="semester" name="semester" required>
              </div>
              <div class="mb-3">
                <label for="totalStudents" class="form-label">Total Students</label>
                <input type="number" class="form-control" id="totalStudents" name="totalStudents" required>
              </div>
              <div class="mb-3">
                <label for="classTime" class="form-label">Class Time</label>
                <input type="text" class="form-control" id="classTime" name="classTime" required>
              </div>
              <div class="mb-3">
                <label for="completionNote" class="form-label">Completion Note</label>
                <textarea class="form-control" id="completionNote" name="completionNote" rows="3"></textarea>
              </div>
              <div class="mb-3">
                <label for="classroom" class="form-label">Classroom</label>
                <input type="text" class="form-control" id="classroom" name="classroom" required>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Add Subject</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <!-- Edit Subject Modal -->
    <div class="modal fade" id="editSubjectModal" tabindex="-1" aria-labelledby="editSubjectModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editSubjectModalLabel">Edit Subject</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="EditSubjectServlet" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="originalName" id="editOriginalName">
                        <input type="hidden" name="semester" value="<%=request.getAttribute("semester")%>">
                        <!-- Hidden Field for Subject ID -->
						<input type="hidden" id="editSubjectId" name="subjectId">

                        <input type="hidden" name="teacherId" value="<%=session.getAttribute("t_id")%>">
                        <div class="mb-3">
                            <label for="editCompletionNote" class="form-label">Completion Note</label>
                            <textarea class="form-control" id="editCompletionNote" name="completionNote"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="editTotalStudents" class="form-label">Total Students</label>
                            <input class="form-control" id="editTotalStudents" name="totalStudents" type="number">
                        </div>
                        <div class="mb-3">
                            <label for="editClassroom" class="form-label">Classroom</label>
                            <input type="text" class="form-control" id="editClassroom" name="classroom">
                        </div>
                        <div class="mb-3">
                            <label for="editClassTime" class="form-label">Class Time</label>
                            <input type="text" class="form-control" id="editClassTime" name="classTime">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

   	<script>
   	function populateEditForm(subjectName, completionNote, classroom, timing, totalStudents, subjectId) {
   	    document.getElementById('editCompletionNote').value = completionNote;
   	    document.getElementById('editClassroom').value = classroom;
   	    document.getElementById('editClassTime').value = timing;
   	    document.getElementById('editTotalStudents').value = totalStudents;
   	    document.getElementById('editSubjectId').value = subjectId;  
   	    document.getElementById('editOriginalName').value = subjectName;  // THIS WAS MISSING
   	}

</script>


    <!-- Add these scripts at the end of the body -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
    
</body>
</html>
