<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/sale.css">
</head>
<body>
<div id="background"></div>
<%@ include file="/WEB-INF/header.jsp" %>
<section id="main">
    <% sale = JspHelper.<Sale>getAttributeObject(request, "sale");
        pageContext.setAttribute("user", user);%>
    <div class="sale sale-mine">
        <div class="status status-${sale.status.ordinal()}">${sale.status}</div>
        <c:if test="${(sale.vendeur.id.equals(user.id) && sale.status != Status.SOLD) || user.administrateur}">
            <div class="edit-link"><a href="${pageContext.request.contextPath}/offer/edit?id=${sale.id}"><i
                    class="fa fa-pencil-alt"></i></a>
            </div>
        </c:if>
        <div class="views">${sale.nbVues}</div>
        <div class="content">
            <div class="title">${sale.titre}</div>
            <div class="author">${sale.vendeur.nom.equals("") ? sale.vendeur.id : sale.vendeur.nom}
                <c:if test="${sale.vendeur.telephone != null && !sale.vendeur.telephone.equals(\"\")}">
                    (${sale.vendeur.telephone})
                </c:if>
            </div>
            <div class="description">${sale.description}</div>
            <div class="price">${sale.prix}€</div>
            <div class="date">
                <div>Created : <%=
                DateFormat
                        .getDateInstance(DateFormat.SHORT, Locale.FRANCE)
                        .format(sale.getCreation())
                %>
                </div>
                <div>Modified : <%=
                DateFormat
                        .getDateInstance(DateFormat.SHORT, Locale.FRANCE)
                        .format(sale.getModification())
                %>
                </div>
                <% if (sale.getStatus() == Status.SOLD) { %>
                <div>Sold : <%=
                DateFormat
                        .getDateInstance(DateFormat.SHORT, Locale.FRANCE)
                        .format(sale.getAchat())
                %><% } %>
                </div>
            </div>
        </div>
    </div>
    <div class="buttons">
        <a class="info-cancel" href="#">Report</a>
        <span class="clear"></span>
        <%
            switch (sale.getStatus()) {
                case PUBLISHED:
                    if (user != null && sale.getVendeur().getId().equals(user.getId())) {
                        // User is owner and the sale is published
        %> <a class="info-cancel" href="${pageContext.request.contextPath}/offer/edit?do=delete&id=${sale.id}">Cancel
        sale</a> <%
    } else {
    %> <a class="info-buy" href="${pageContext.request.contextPath}/offer/edit?do=buy&id=${sale.id}">Buy</a> <%
            }
            ;
            break;
        case SOLD:
    %> <a class="info-bough" href="">Bought by : <%= sale.getAcheteur().getNom() %>
    </a> <%
            break;
        case TEMPORARY:
        case CANCELED:
            if (user != null && sale.getVendeur().getId().equals(user.getId())) {
    %> <a class="info-publish"
          href="${pageContext.request.contextPath}/offer/edit?do=publish&id=${sale.id}">Publish</a> <%
                }
                break;
        } %>
    </div>
</section>
</body>
<script>
</script>
</html>
