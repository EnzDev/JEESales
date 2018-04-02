<%@ include file="/WEB-INF/variableInjector.jsp" %>
<div>
    <h1>Le Bon JavaEE</h1>
    <nav>
        <span></span>
        <span class="${pageContext.request.servletPath.contains("index.js") ? "selected" : ""}"><a href="${pageContext.request.contextPath}/">Home</a></span>
        <span class="${pageContext.request.servletPath}"><a href="${pageContext.request.contextPath}/offers">My offers</a></span>
        <span class="${pageContext.request.servletPath}"><a href="${pageContext.request.contextPath}/account">My account</a></span>
        <span class="clear"></span>

        <% if (isLoggedIn) { %>
        <span>Welcome <%= user.getNom().equals("") ? user.getId() : user.getNom() %></span>
        <% } else { %>
        <span><a href="${pageContext.request.contextPath}/login">Login</a></span>
        <% } %>
        <%
            if(user != null && user.isAdministrateur()){ %>
        <span><a href="${pageContext.request.contextPath}/admin">Administration</a></span>
        <% } %>

    </nav>

</div>