<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>My account</title>
    <%@ include file="/WEB-INF/head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/sale.css">
    <style>
        .sale.sale-mine {
            display: grid;
            grid-template-columns: 5% 10% 5% 75% 5%;
            grid-template-rows: repeat(6, 2em);
            align-items: center;
            align-content: space-around;
        }

        .sale-mine label {
            justify-self: end;
        }

        .sale-mine input {
            justify-self: start;
            height: 2em;
        }

        input:disabled { background: transparent; }
        input:read-only { background: transparent; }
        input:-moz-read-only { background: transparent; }

        .sale-mine input:not(:last-child){
            width: 100%;
            border-radius: 5px;
            padding-left: 15px;
        }
    </style>
</head>
<body>
<div id="background"></div>
<%@ include file="/WEB-INF/header.jsp" %>
<form id="main" action="${pageContext.request.contextPath}/account" method="post">
    <% pageContext.setAttribute("user", request.getAttribute("editUser"));
        pageContext.setAttribute("loggeduser", user);%>
    <div class="sale sale-mine">
        <div></div><label for="id"       >Id</label      ><div></div><input autocomplete="off" type="text" readonly value="${user.id}" placeholder="Id" id="id" name="id"><div></div>
        <div></div><label for="name"     >Name</label    ><div></div><input autocomplete="off" type="text" value="${user.nom}" placeholder="Name" id="name" name="name"><div></div>
        <div></div><label for="password" >Password</label><div></div><input autocomplete="off" type="password" value="" placeholder="Password" id="password" name="password"><div></div>
        <div></div><label for="rpassword">Repeat</label  ><div></div><input autocomplete="off" type="password" value="" placeholder="Repeat your password" id="rpassword" name="password"><div></div>
        <div></div><label for="phone"    >Phone</label   ><div></div><input autocomplete="off" type="text" value="${user.telephone}" placeholder="Phone" id="phone" name="phone"><div></div>
        <div></div><label for="admin"    >Admin?</label  ><div></div><input autocomplete="off" type="checkbox" ${loggeduser.administrateur ? "" : "disabled"} ${user.administrateur ? "checked" : ""} placeholder="Admin" id="admin" name="admin"><div></div>

    </div>
    <div class="buttons">
        <input type="submit" class="info-publish" value="Save"/>
    </div>
</form>
</body>
<script>

</script>
</html>
