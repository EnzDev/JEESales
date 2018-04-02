package fr.enzomallard.app;

import javax.servlet.http.HttpServletRequest;

public class JspHelper {
    public static boolean getSessionBoolean(HttpServletRequest request, String attr) {
        return getSessionBoolean(request, attr, false);
    }

    public static boolean getSessionBoolean(HttpServletRequest request, String attr, boolean defaultValue) {
        Object resolvedAttribute = resolveSession(request, attr);
        if (resolvedAttribute == null) return defaultValue; // Default value
        else return (boolean) resolvedAttribute;
    }

    @SuppressWarnings("unchecked") // Ignoring unsafe cast for this purpose (like findById)
    public static <T> T getSessionObject(HttpServletRequest request, String attr) {
        return (T) resolveSession(request, attr);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getAttributeObject(HttpServletRequest request, String attr) {
        return (T) resolveAttribute(request, attr);
    }

    private static Object resolveSession(HttpServletRequest request, String attr) {
        return request.getSession().getAttribute(attr);
    }

    private static Object resolveAttribute(HttpServletRequest request, String attr) {
        return request.getAttribute(attr);
    }
}
