package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.Organization;
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
@PrepareForTest({HTTPConnection.class, OrganizationRequests.class})
public class OrganizationRequestsTest {

    final Organization organization = new Organization(1, "University of test");

    @Test
    public void testGetOrganizationByOrganizationID() throws Exception {

        String expectedResponse = "{\"id\":" + organization.getId()
                + ",\"organizationName\":\"" + organization.getOrganizationName() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(organization.toString(), OrganizationRequests.getOrganizationById(organization.getId()).toString());
    }

    @Test
    public void testGetOrganizationByOrganizationName() throws Exception {

        String expectedResponse = "{\"id\":" + organization.getId()
                + ",\"organizationName\":\"" + organization.getOrganizationName() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(organization.toString(), OrganizationRequests.getOrganizationByName(organization.getOrganizationName()).toString());
    }

    @Test
    public void testGetAllOrganization() throws Exception{

        int organizationID = 1;

        Organization organization1 = new Organization(2, "University of test2");
        Organization organization2 = new Organization(3, "University of test3");

        List<Organization> expectedList = new ArrayList<Organization>() {{
            add(organization1);
            add(organization2);
        }};

        String expectedResponse = "[{\"id\":" + organization1.getId()
                + ",\"organizationName\":\"" + organization1.getOrganizationName() + "\"},"
                + "{\"id\":" + organization2.getId()
                + ",\"organizationName\":\"" + organization2.getOrganizationName() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), OrganizationRequests.getAllOrganizations().toString());

    }
    @Test
    public void testCreateOrganization() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, OrganizationRequests.createOrganization(organization));
    }

    @Test
    public void testUpdateOrganizationById() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, OrganizationRequests.updateOrganization(organization));
    }

    @Test
    public void testDeleteOrganizationById() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, OrganizationRequests.deleteOrganizationById(organization.getId()));
    }
}
