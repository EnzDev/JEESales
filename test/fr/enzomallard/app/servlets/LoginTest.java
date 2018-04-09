package fr.enzomallard.app.servlets;

import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.User;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Enzo Mallard
 * The 09/04/2018
 */
public class LoginTest {
    @Before
    public void prepare(){
        HttpUnitOptions.setScriptingEnabled(false);
    }

    @Test
    public void loginErrorPassword() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        PostMethodWebRequest req = new PostMethodWebRequest( "http://localhost:8080/login" );

        req.setParameter("email","user@epsi.fr");
        req.setParameter("password","bach3_"); // BadPassword
        WebResponse res = wc.getResponse(req);
        assertTrue(res.isHTML());
        assertEquals(res.getURL().getPath(), "/login"); // Still on login
        assertEquals(res.getElementsWithClassName("error").length, 1); // Has error
    }

    @Test
    public void loginErrorLogin() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        PostMethodWebRequest req = new PostMethodWebRequest( "http://localhost:8080/login" );

        req.setParameter("email","us_e_r@epsi.fr"); // BadLogin
        req.setParameter("password","bach3");
        WebResponse res = wc.getResponse(req);
        assertTrue(res.isHTML());
        assertEquals(res.getURL().getPath(), "/login"); // Still on login
        assertEquals(res.getElementsWithClassName("error").length, 1); // Has error
    }


    @Test
    public void loginSuccess() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        PostMethodWebRequest req = new PostMethodWebRequest( "http://localhost:8080/login" );


        req.setParameter("email","user@epsi.fr");
        req.setParameter("password","bach3");
        WebResponse res = wc.getResponse(req);
        assertTrue(res.isHTML());
        assertEquals(res.getURL().getPath(), "/"); // Has been redirected successfully to /
        assertEquals(res.getElementsWithClassName("error").length, 0); // No class error

    }
}