<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="fr.enzomallard.app.DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <%@ include file="/WEB-INF/head.jsp" %>
</head>
<body>
<div id="background"></div>
<%@ include file="/WEB-INF/header.jsp" %>
<section id="sales" class="grid <%= JspHelper.getSessionBoolean(request, "isLoggedIn") ? "both" : "alone"%>">
    <% pageContext.setAttribute("seeableSales", DaoFactory.getInstance()
            .getSaleDao()
            .getFrom(user, true));
        pageContext.setAttribute("user", user);
    %>
    <c:forEach items="${seeableSales}" var="sale">
        <div class="sale" @click="open('${sale.id}')">
            <div class="status status-${sale.status.ordinal()}">
                    ${sale.status}
            </div>
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

</body>
<script>
    doopen = (id)=>{window.location = `/offer/view?id=\${id}`};
    var seeable = new Vue({
        el: '#sales',
        methods: { open: doopen }
    });

</script>
</html>
