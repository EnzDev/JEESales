<jsp:useBean id="sale" scope="request" type="fr.enzomallard.app.beans.Sale"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.enzomallard.app.beans.Sale" %>
<%@ page import="fr.enzomallard.app.beans.Status" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <%@ include file="/WEB-INF/head.jsp" %>
</head>
<body>
<div id="background"></div>
<%@ include file="/WEB-INF/header.jsp" %>
<section id="main">
    <% sale = JspHelper.<Sale>getAttributeObject(request, "sale");%>
    <div class="sale-mine">
        <div class="status-${sale.status.ordinal()}">${sale.status}</div>
        <div><a href="${pageContext.request.contextPath}/offer/edit?id=${sale.id}">EDIT</a></div>
        <div class="title">${sale.titre}</div>
        <div class="views">${sale.nbVues}</div>
        <div class="author">${sale.vendeur.nom}</div>
        <div class="description">${sale.description}</div>
        <div class="price">${sale.prix}€</div>
        <div class="date">Created : <%=
        DateFormat
                .getDateInstance(DateFormat.SHORT, Locale.FRANCE)
                .format(sale.getCreation())
        %>
        </div>
        <div class="date">Modified : <%=
        DateFormat
                .getDateInstance(DateFormat.SHORT, Locale.FRANCE)
                .format(sale.getModification())
        %>
        </div>
        <% if (sale.getStatus() == Status.VENDU) { %>
        <div class="date">Sold : <%=
        DateFormat
                .getDateInstance(DateFormat.SHORT, Locale.FRANCE)
                .format(sale.getAchat())
        %><% } %>
        </div>
    </div>
    <div class="buttons">
        <a href="#">Report</a>
        <span class="clear"></span>
        <%
            switch (sale.getStatus()) {
                case PUBLIE:
                    if (user != null && sale.getVendeur().getId().equals(user.getId())) {
                        // User is owner and the sale is published
                        %> <a href="${pageContext.request.contextPath}/offer/edit?do=delete&id=${sale.id}">Cancel sale</a> <%
                    } else {
                        %> <a href="${pageContext.request.contextPath}/offer/edit?do=buy&id=${sale.id}">Buy</a> <%
                    };
                    break;
                case VENDU:
                    %> <a href="">Bought by : <%= sale.getAcheteur().getNom() %> </a> <%
                    break;
                case TEMPORAIRE:
                    if (user != null && sale.getVendeur().getId().equals(user.getId())) {
                        %> <a href="${pageContext.request.contextPath}/offer/edit?do=publish&id=${sale.id}">Publish</a> <%
                    }
                    break;
        } %>
    </div>
</section>
</body>
<script>
</script>
</html>
