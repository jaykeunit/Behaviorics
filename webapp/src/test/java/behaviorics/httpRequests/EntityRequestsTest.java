package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.Building;
import behaviorics.models.Entity;
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
@PrepareForTest({HTTPConnection.class, EntityRequests.class})
public class EntityRequestsTest {

    final Entity entity = new Entity(1, 2, "test entity", "5555555555", "E1");

    @Test
    public void testGetEntityByEntityName() throws Exception {
        String expectedResponse = "{\"id\":" + entity.getID()
                + ",\"organizationID\":" + entity.getOrganizationID()
                + ",\"entityName\":\"" + entity.getEntityName()
                + "\",\"contactNumber\":\"" + entity.getContactNumber()
                + "\",\"entityAcronym\":\"" + entity.getEntityAcronym() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(entity.getID(), EntityRequests.getEntityByEntityName(entity.getEntityName()).getID());
        Assert.assertEquals(entity.getOrganizationID(), EntityRequests.getEntityByEntityName(entity.getEntityName()).getOrganizationID());
        Assert.assertEquals(entity.getEntityName(), EntityRequests.getEntityByEntityName(entity.getEntityName()).getEntityName());
        Assert.assertEquals(entity.getContactNumber(), EntityRequests.getEntityByEntityName(entity.getEntityName()).getContactNumber());
        Assert.assertEquals(entity.getEntityAcronym(), EntityRequests.getEntityByEntityName(entity.getEntityName()).getEntityAcronym());
    }

    @Test
    public void testGetAllEntitiesByOrganizationID() throws Exception {
        int orgId = 2;
        Entity entity1 = new Entity(1, orgId, "test entity", "5555555555", "E1");
        Entity entity2 = new Entity(2, orgId, "test entity", "5555555555", "E1");

        List<Entity> expectedList = new ArrayList<Entity>() {{
            add(entity1);
            add(entity2);
        }};

        String expectedResponse = "[{\"id\":" + entity1.getID()
                + ",\"organizationID\":" + entity1.getOrganizationID()
                + ",\"entityName\":\"" + entity1.getEntityName()
                + "\",\"contactNumber\":\"" + entity1.getContactNumber()
                + "\",\"entityAcronym\":\"" + entity1.getEntityAcronym() + "\"},"
                + "{\"id\":" + entity2.getID()
                + ",\"organizationID\":" + entity2.getOrganizationID()
                + ",\"entityName\":\"" + entity2.getEntityName()
                + "\",\"contactNumber\":\"" + entity2.getContactNumber()
                + "\",\"entityAcronym\":\"" + entity2.getEntityAcronym() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), EntityRequests.getAllEntitiesByOrganizationID(orgId).toString());
    }

    @Test
    public void testCreateEntity() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, EntityRequests.createEntity(entity));
    }

    @Test
    public void testUpdateEntityById() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, EntityRequests.updateEntityById(entity));
    }

    @Test
    public void testDeleteEntityById() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, EntityRequests.deleteEntityById(entity.getID()));
    }
}
