<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>Version</title>
  </head>
  <body>
    <h1>Server started</h1>
    <b>Tomcat version <%= (String)request.getAttribute("version") %></b><br>
    <small>Page generated the <%= new Date() %></small>
  </body>
</html>