<%@ include file="/WEB-INF/variableInjector.jsp" %>
<div>
    <h1>Le Bon JavaEE</h1>
    <nav>
        <span></span>
        <span class="${pageContext.request.servletPath.contains("index.js") ? "selected" : ""}"><a href="${pageContext.request.contextPath}/">Home</a></span>
        <span class="${pageContext.request.servletPath.contains("sale") ? "selected" : ""}"><a href="${pageContext.request.contextPath}/offers">My offers</a></span>
        <span class="${pageContext.request.servletPath.contains("account") ? "selected" : ""}"><a href="${pageContext.request.contextPath}/account">My account</a></span>
        <span class="clear"></span>

        <% if (isLoggedIn) { %>
        <span><a href="${pageContext.request.contextPath}/logout">Welcome <%= user.getNom().equals("") ? user.getId() : user.getNom() %></a></span>
        <% } else { %>
        <span><a href="${pageContext.request.contextPath}/login">Login</a></span>
        <% } %>
        <%
            if(user != null && user.isAdministrateur()){ %>
        <span><a href="${pageContext.request.contextPath}/admin">Administration</a></span>
        <% } %>

    </nav>

</div>