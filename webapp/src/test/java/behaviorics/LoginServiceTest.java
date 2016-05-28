package behaviorics;

import behaviorics.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore ("javax.crypto.*")
public class LoginServiceTest {

    private LoginService manager;

    @Before
    public void setUp() throws Exception {
        manager = new LoginService();
    }

    // Test case
    // password - "SDPCougars"
    // hash - byte array [1-32]
    @Test
    public void testPasswordSalting() {
        byte[] test = new byte[] {-70, -56, 72, 100, 101, -93, -74, -82, -41, 32, 125, 12, -86, -5, -115, 41, 78,
                122, 19, 15, 111, -11, -45, -55, 12, 32, -77, -12, 23, 14, -41, 54};

        byte[] salt = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32};
        String password = "SDPCougars";
        byte[] result = manager.salt(password, salt);
        assertTrue(Arrays.equals(test,result));
    }

    @Test
    public void testValidUserWithCorrectPassword() {
        User user = new User();

        byte[] testSalt = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32};
        byte[] testHash = new byte[] {-70, -56, 72, 100, 101, -93, -74, -82, -41, 32, 125, 12, -86, -5, -115, 41, 78,
                122, 19, 15, 111, -11, -45, -55, 12, 32, -77, -12, 23, 14, -41, 54};

        user.setSalt(testSalt);
        user.setKey("SDPCougars");
        user.setHash(testHash);

        assertTrue(manager.validUser(user));
    }

    @Test
    public void testValidUserWithIncorrectPassword() {
        User user = new User();

        byte[] testSalt = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32};
        byte[] testHash = new byte[] {-70, -56, 72, 100, 101, -93, -74, -82, -41, 32, 125, 12, -86, -5, -115, 41, 78,
                122, 19, 15, 11, -11, -45, -55, 12, 32, -77, -12, 23, 14, -41, 54};

        user.setSalt(testSalt);
        user.setKey("SDPCougars");
        user.setHash(testHash);

        assertFalse(manager.validUser(user));
    }

    /*
    @Test
    public void testValidUserWithCorrectPassword() {
        User user = new User();
        user.setKey("SDPCougars");
        user.setUserName("mockUser");

        Map<String, byte[]> mockUser = new HashMap<>();
        byte[] testSalt = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32};
        byte[] testHash = new byte[] {-70, -56, 72, 100, 101, -93, -74, -82, -41, 32, 125, 12, -86, -5, -115, 41, 78,
                122, 19, 15, 111, -11, -45, -55, 12, 32, -77, -12, 23, 14, -41, 54};

        mockUser.put("salt", testSalt);
        mockUser.put("hash", testHash);

        LoginService manager = Mockito.mock(LoginService.class);
        when(manager.getUser("mockUser")).thenReturn(mockUser);
        when(manager.validUser(user)).thenCallRealMethod();
        when(manager.salt("SDPCougars",testSalt )).thenCallRealMethod();

        assertTrue(manager.validUser(user));

    }

    @Test
    public void testValidUserWithIncorrectPassword() {
        User user = new User();
        user.setKey("SDPCougars");
        user.setUserName("mockUser");

        Map<String, byte[]> mockUser = new HashMap<>();
        byte[] testSalt = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32};
        byte[] testHash = new byte[] {0, 0, 0, 0, 101, 93, 0, 0, 0, 0, 0, 0, 0, -5, -115, 41, 78,
                122, 0, 0, 0, -0, -45, 0, 12, 32, -77, -12, 23, 14, -41, 54};

        mockUser.put("salt", testSalt);
        mockUser.put("hash", testHash);

        LoginService manager = Mockito.mock(LoginService.class);
        when(manager.getUser("mockUser")).thenReturn(mockUser);
        when(manager.validUser(user)).thenCallRealMethod();
        when(manager.salt("SDPCougars",testSalt )).thenCallRealMethod();

        assertFalse(manager.validUser(user));

    }


    //continue from here. (need DB next)
    @Test
    public void testGetUser(){
        Map<Object, Object> mockUser = new HashMap<>();
        assertEquals(mockUser,manager.getUser("not a real guy"));
    }
    */

    @PrepareOnlyThisForTest(SecretKeyFactory.class)
    @Test
    public void testPasswordSaltingCatchesNoSuchAlgorithmException() {
        byte[] salt = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 32};
        String password = "SDPCougars";

        PowerMockito.mockStatic(SecretKeyFactory.class);
        try {
            Mockito.when(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")).thenThrow(new NoSuchAlgorithmException());
        } catch (NoSuchAlgorithmException e) {
            assertFalse(true); //if this catches then something is wrong with test
        }

        byte[] result = manager.salt(password, salt);
        assertTrue(Arrays.equals(null,result));
    }

}