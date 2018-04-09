<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="fr.enzomallard.app.DaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <%@ include file="head.jsp" %>
    <style>
        .sale .content {
            left: 5px;
            width: calc(100% - 10px);
        }

        .sale {
            height: 4em;
        }
    </style>
</head>
<body>
<div id="background"></div>
<%@ include file="header.jsp" %>
<section id="sales-seeable" class="grid <%= JspHelper.getSessionBoolean(request, "isLoggedIn") ? "both" : "alone"%>">
    <% pageContext.setAttribute("users", DaoFactory.getInstance()
            .getUserDao()
            .getAll());
        pageContext.setAttribute("user", user);
    %>
    <c:forEach items="${users}" var="user">
        <div class="sale" @click="open('${user.id}')">
            <div class="status status-${user.administrateur ?  3 : 1}">
                    ${user.id}
            </div>
            <div class="content">
                <div class="views">${user.administrateur ? "admin" : ""}</div>
                <div class="name">${user.nom}</div>
                <div class="price">${user.telephone}</div>
            </div>
        </div>
    </c:forEach>
</section>
</body>
<script>
    doopen = (id) => {
        window.location = `/account?userid=\${id}`
    };
    var seeable = new Vue({
        el: '#sales-seeable',
        methods: {open: doopen}
    });
</script>
</html>
