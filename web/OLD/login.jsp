<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
    <%@ include file="../head.jsp" %>
</head>
<body>
<div id="background"></div>
<div id="needlog">
    <%@ include file="../header.jsp" %>
    You must be logged in to use this website <br>
    Please log in or register to continue further.

    <div id="select" class="flex-c">
        <form action="${pageContext.request.contextPath}/login" method="post" v-if="showLogin" class="flex-c">
            <input type="text" name="email" placeholder="email"><br>
            <input type="password" name="password" placeholder="password"><br>
            <input type="submit" title="Login" value="Login">
            <div @click="showLogin=false">New here ? Click to register.</div>
        </form>
        <form action="${pageContext.request.contextPath}/register" method="post" v-if="!showLogin" class="flex-c">
            <input type="text" name="email" placeholder="email"><br>
            <input type="password" name="password" placeholder="password"><br>
            <input type="password" name="password" placeholder="confirm your password"><br>
            <input type="submit" title="Register" value="Register">
            <div @click="showLogin=true">Already have an account ? Click to login</div>
        </form>
    </div>

</div>
</body>
<script>
    vueapp = new Vue({
        el: '#needlog',
        data: {
            showLogin: true
        }
    });
</script>
</html>
