<%@ page import="fr.enzomallard.app.beans.User" %>
<%@ page import="fr.enzomallard.app.JspHelper" %>
<%
    User user = JspHelper.getSessionObject(request, "user");
    boolean isLoggedIn = JspHelper.getSessionBoolean(request, "isLoggedIn");
%>