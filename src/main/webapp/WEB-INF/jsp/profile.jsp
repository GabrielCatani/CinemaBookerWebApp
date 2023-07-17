<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <%
      String sessionID = session.getId();
    %>
</head>
<body>
    <h1>Profile!</h1><br>
    <h2>Your session ID is <%=sessionID %></h2>
</body>
</html>