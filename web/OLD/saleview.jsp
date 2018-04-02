<%@ page import="fr.enzomallard.app.beans.Sale" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.enzomallard.app.beans.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <% { Sale sale = (Sale) request.getAttribute("sale"); %>
    <dl>
        <dt>Title</dt>
        <dd><% out.print(sale.getTitre()); %></dd>
        <dt>Seller</dt>
        <dd><% out.print(sale.getVendeur().getId()); %></dd>
        <dt>Created</dt>
        <dd><% out.print(sale.getCreation()); %></dd>

        <dt>Availability</dt>
        <dd><% out.print(Status.values()[sale.getStatus()]); %></dd>
    </dl>
    <% } %>
</div>
</body>
</html>
