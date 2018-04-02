<%@ include file="/variableInjector.jsp" %>
<div>
    <h1>Le Bon JavaEE</h1>
    <nav>
        <span class="clear"></span>
        <span><a href="${pageContext.request.contextPath}/">Home</a></span>
        <span><a href="${pageContext.request.contextPath}/offers">My offers</a></span>
        <span><a href="${pageContext.request.contextPath}/account">My account</a></span>
        <span class="clear"></span>

        <% if (isLoggedIn) { %>
        <span>Welcome <%= user.getNom() %></span>
        <% } else { %>
        <span><a href="${pageContext.request.contextPath}/login">Login</a></span>
        <% } %>
        <%
            if(user != null && user.isAdministrateur()){ %>
        <span><a href="${pageContext.request.contextPath}/admin">Administration</a></span>
        <% } %>

    </nav>

</div>