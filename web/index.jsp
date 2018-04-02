<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="fr.enzomallard.app.DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <%@ include file="head.jsp" %>
</head>
<body>
<div id="background"></div>
<%@ include file="header.jsp" %>
<section id="sales-seeable" class="grid">
    <% pageContext.setAttribute("seeableSales", DaoFactory.getInstance()
            .getSaleDao()
            .get(JspHelper.<User>getSessionObject(request, "user")));
    %>
    <c:forEach items="${seeableSales}" var="sale">
        <div class="sale">
            <div class="status status-${sale.status.ordinal()}">${sale.status}</div>
            <div class="content">
                <div class="title">${sale.titre}</div>
                <div class="views">${sale.nbVues}</div>
                <div class="description">${sale.description}</div>
                <div class="price">${sale.prix}€</div>
                <div class="date">
                    <fmt:formatDate dateStyle="short" type="date" value="${sale.modification}"/>
                </div>
            </div>
        </div>
    </c:forEach>
</section>

<% if (JspHelper.getSessionBoolean(request, "isLoggedIn")) { %>
<section id="sales-mine-list">
    <% pageContext.setAttribute("mySales", DaoFactory.getInstance()
            .getSaleDao()
            .getFrom(user, true)); %>
    <c:forEach items="${mySales}" var="sale">
        <div class="sale-mine">
            <div class="status-${sale.status.ordinal()}">${sale.status}</div>
            <div class="title">${sale.titre}</div>
        </div>
    </c:forEach>
</section>
<% } %>
</body>
<script>
</script>
</html>
