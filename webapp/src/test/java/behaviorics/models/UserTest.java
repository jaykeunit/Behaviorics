package behaviorics.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void canary() {
        assertTrue(true);
    }

    @Test
    public void testSetUsername() {
        user.setUserName("Tester");
        User testUser = user;
        assertEquals(user, testUser);
    }

    @Test
    public void testGetUsername() {
        user.setUserName("tester");
        assertEquals("tester",user.getUserName());
    }

    @Test
    public void testSetKey() {
        user.setKey("password");
        User testUser = user;
        assertEquals(user, testUser);
    }

    @Test
    public void testGetKey() {
        user.setKey("NewKey");
        assertEquals("NewKey", user.getKey());
    }
}