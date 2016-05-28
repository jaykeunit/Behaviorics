package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.User;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, UserRequests.class})
public class UserRequestsTest {

    final User user = new User(1, "admin", "admin", 1, "University of Houston");

    @Test
    public void testGetUserByUserName() throws Exception {

        user.setHash("123".getBytes());
        user.setSalt("123".getBytes());

        String expectedResponse = "{\"id\":" + user.getId()
                + ",\"userName\":\"" + user.getUserName()
                + "\",\"salt\":\"" + Base64.encodeBase64String(user.getSalt())
                + "\",\"hash\":\"" + Base64.encodeBase64String(user.getHash())
                + "\",\"privilege\":\"" + user.getPrivilege()
                + "\",\"organizationID\":" + user.getOrganizationID()
                + ",\"organizationName\":\"" + user.getOrganizationName() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(user.toString(), UserRequests.getUserByUsername(user.getUserName()).toString());
    }

    @Test
    public void testGetAllBuildingsByOrganizationID() throws Exception{

        int organizationID = 1;

        User user1 = new User(1, "admin", "admin", organizationID, "University of Houston");
        User user2 = new User(2, "bob", "admin", organizationID, "University of Houston");

        user1.setHash("123".getBytes());
        user1.setSalt("123".getBytes());

        user2.setHash("123".getBytes());
        user2.setSalt("123".getBytes());

        List<User> expectedList = new ArrayList<User>() {{
            add(user1);
            add(user2);
        }};

        String expectedResponse = "[{\"id\":" + user.getId()
                + ",\"userName\":\"" + user1.getUserName()
                + "\",\"salt\":\"" + Base64.encodeBase64String(user1.getSalt())
                + "\",\"hash\":\"" + Base64.encodeBase64String(user1.getHash())
                + "\",\"privilege\":\"" + user1.getPrivilege()
                + "\",\"organizationID\":" + user1.getOrganizationID()
                + ",\"organizationName\":\"" + user1.getOrganizationName() + "\"},"
                + "{\"id\":" + user2.getId()
                + ",\"userName\":\"" + user2.getUserName()
                + "\",\"salt\":\"" + Base64.encodeBase64String(user2.getSalt())
                + "\",\"hash\":\"" + Base64.encodeBase64String(user2.getHash())
                + "\",\"privilege\":\"" + user2.getPrivilege()
                + "\",\"organizationID\":" + user2.getOrganizationID()
                + ",\"organizationName\":\"" + user2.getOrganizationName() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), UserRequests.getAllUsersByOrganizationID(organizationID).toString());

    }

    @Test
    public void testCreateUser() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, UserRequests.createUser(user));
    }

    @Test
    public void testUpdateUserById() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, UserRequests.updateUserById(user));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, UserRequests.deleteUserById(user.getId()));
    }
}
