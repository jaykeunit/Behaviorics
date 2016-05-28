package behaviorics.users;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class UserBOTest {

    private UserBO userBO;
    private int testID = 1;
    private String testUserName = "UserName";
    private byte[] testSalt = new byte[10];
    private byte[] testHash = new byte[10];
    private String testPrivilege = "admin";
    private int testOrganizationID = 1;
    private String testOrganizationName = "test";

    @Before 
    public void setUp(){

    	userBO = new UserBO(testID, testUserName, testSalt, testHash, testPrivilege, testOrganizationID);
    }

    @Test
    public void testEmptyConstructor(){
    	userBO = new UserBO();
    	assertEquals(0, userBO.getId());
    }

    @Test
    public void testConstructor(){
        UserBO userBO1 = new UserBO(testID, testUserName, testPrivilege, testOrganizationID, testOrganizationName);

        assertEquals(userBO1.getHash(), null);
    }

    @Test
    public void testGetID() {
        assertEquals(testID, userBO.getId());
    }

    @Test
    public void testGetUserName() {
        assertEquals(testUserName, userBO.getUserName());
    }

    @Test
    public void testGetSalt() {
        assertTrue(Arrays.equals(testSalt, userBO.getSalt()));
    }

    @Test
    public void testGetHash() {
        assertTrue(Arrays.equals(testHash, userBO.getHash()));
    }

    @Test
    public void testGetPrivilege() {
        assertEquals(testPrivilege, userBO.getPrivilege());
    }

    @Test
    public void testGetOrganizationID() {
        assertEquals(testOrganizationID, userBO.getOrganizationID());
    }

    @Test
    public void testSetOrganizationName(){
        userBO.setOrganizationName(testOrganizationName);

        assertEquals(userBO.getOrganizationName(), testOrganizationName);
    }

    @Test
    public void testToString() {
        assertEquals("id: "+ testID + " " 
        + "userName: "+ testUserName + " "
        + "salt: " + Arrays.toString(testSalt) + " "
        + "hash: " + Arrays.toString(testHash) + " "
        + "privilege: "+ testPrivilege + " " 
        + "organizationID: "+ testOrganizationID, userBO.toString());

    }

}