<%@ page import="fr.enzomallard.app.beans.Sale" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.enzomallard.app.beans.Status" %>
<%@ page import="fr.enzomallard.app.JspHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <% for (Sale sale : JspHelper.<List<Sale>>getSessionObject(request, "sales")) { %>
        <tr>
            <td><%= sale.getTitre() %></td>
            <td><%= sale.getVendeur().getId() %></td>
            <td><%= Status.values()[sale.getStatus()] %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
