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

    public static boolean getAttributeBoolean(HttpServletRequest request, String attr) {
        return getAttributeBoolean(request, attr, false);
    }

    public static boolean getAttributeBoolean(HttpServletRequest request, String attr, boolean defaultValue) {
        Object resolvedAttribute = resolveAttribute(request, attr);
        if (resolvedAttribute == null) return defaultValue; // Default value
        else return (boolean) resolvedAttribute;
    }

    /** Get an attribute of a session with a given type (autocast)
     * @param request The HttpServletRequest containing the attribute
     * @param attr The attribute name
     * @param <T> The given type
     * @return The resolved attribute
     */
    @SuppressWarnings("unchecked") // Ignoring unsafe cast for this purpose (like findById)
    public static <T> T getSessionObject(HttpServletRequest request, String attr) {
        return (T) resolveSession(request, attr);
    }

    /** Get an attribute with a given type (autocast)
     * @param request The HttpServletRequest containing the attribute
     * @param attr The attribute name
     * @param <T> The given type
     * @return The resolved attribute
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAttributeObject(HttpServletRequest request, String attr) {
        return (T) resolveAttribute(request, attr);
    }


    // Resolvers


    /** Resolve an attribute for a given session
     * @param request The HttpServletRequest containing the attribute
     * @param attr The attribute name
     * @return The resolved attribute
     */
    private static Object resolveSession(HttpServletRequest request, String attr) {
        return request.getSession().getAttribute(attr);
    }

    /** Resolve an attribute for a given request
     * @param request The HttpServletRequest containing the attribute
     * @param attr The attribute name
     * @return The resolved attribute
     */
    private static Object resolveAttribute(HttpServletRequest request, String attr) {
        return request.getAttribute(attr);
    }
}
