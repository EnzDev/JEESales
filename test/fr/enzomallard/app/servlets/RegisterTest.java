package fr.enzomallard.app.servlets;

import com.meterware.httpunit.*;
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
 * ==> Ignore any Log4J error here
 * The 09/04/2018
 */
public class RegisterTest {

    @Before
    public void prepare(){
        HttpUnitOptions.setScriptingEnabled(false);
    }

    @Test
    public void registerErrorPassword() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        PostMethodWebRequest req = new PostMethodWebRequest( "http://localhost:8080/register" );

        req.setParameter("email","newuser");
        req.setParameter("password",new String[]{"passworda","passwordb"});
        WebResponse res = wc.getResponse(req);
        assertTrue(res.isHTML());
        assertEquals(res.getElementsWithClassName("error").length, 1); // Has error
    }

    @Test
    public void registerErrorExistingUser() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        PostMethodWebRequest req = new PostMethodWebRequest( "http://localhost:8080/register" );

        req.setParameter("email","admin@epsi.fr");
        req.setParameter("password",new String[]{"password","password"});
        WebResponse res = wc.getResponse(req);
        assertTrue(res.isHTML());
        assertEquals(res.getElementsWithClassName("error").length, 1); // Has error
    }

    @Test
    public void registerSuccess() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        PostMethodWebRequest req = new PostMethodWebRequest( "http://localhost:8080/register" );

        String uid = UUID.randomUUID()+"@epsi.fr";
        req.setParameter("email",uid);
        req.setParameter("password",new String[]{"password","password"});
        WebResponse res = wc.getResponse(req);
        assertTrue(res.isHTML());
        assertEquals(res.getURL().getPath(), "/account"); // Has been redirected successfully to /account
        assertEquals(res.getElementsWithClassName("error").length, 0); // No class error


        User deleteAfterTest = new User(); // Delete the created user after test
        deleteAfterTest.setId(uid);
        DaoFactory.getInstance().getUserDao().delete(deleteAfterTest);
    }
}