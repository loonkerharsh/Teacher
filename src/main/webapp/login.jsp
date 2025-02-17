<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
            font-size:20px;
        }
        body {
			max-width: 500px; /* Set the maximum width of the container */
			margin: auto; /* Center the container horizontally */
		}
    </style>
</head>
<body>
    <h2>Login</h2>

    <%-- Display error message if available --%>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error"><%= errorMessage %></p>
    <% } %>

    <form action="LoginServlet" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Login">
    </form>

    <p>Forgot your password? Contact Administrator.</p>
</body>
</html>
