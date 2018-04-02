package fr.enzomallard.app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "LoginFilter",
        //urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // Verify if user is logged in (but not on the login page
        String uri = ((HttpServletRequest) req).getRequestURI();
        if (!uri.startsWith("/login") &&
                !uri.startsWith("/font") &&
                !uri.startsWith("/style") &&
                ((HttpServletRequest) req).getSession().getAttribute("isLoggedIn") == null) {
            ((HttpServletResponse) resp).sendRedirect("/login");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
