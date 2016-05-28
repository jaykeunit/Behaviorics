package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.FloorPlan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, FloorPlanRequests.class})
public class FloorPlanRequestsTest {

    final FloorPlan floorPlan = new FloorPlan(1, (byte[]) null, 2);

    @Test
    public void testGetFloorPlanById() throws Exception {
        String expectedResponse = "{\"id\":" + floorPlan.getId()
                + ",\"image\":" + floorPlan.getImage()
                + ",\"floorId\":" + floorPlan.getFloorId() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(floorPlan.getId(), FloorPlanRequests.getFloorPlanById(floorPlan.getId()).getId());
        Assert.assertEquals(floorPlan.getImage(), FloorPlanRequests.getFloorPlanById(floorPlan.getId()).getImage());
        Assert.assertEquals(floorPlan.getFloorId(), FloorPlanRequests.getFloorPlanById(floorPlan.getId()).getFloorId());
    }

    @Test
    public void testGetFloorPlanByFloorId() throws Exception {
        String expectedResponse = "{\"id\":" + floorPlan.getId()
                + ",\"image\":" + floorPlan.getImage()
                + ",\"floorId\":" + floorPlan.getFloorId() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(floorPlan.getId(), FloorPlanRequests.getFloorPlanByFloorId(floorPlan.getFloorId()).getId());
        Assert.assertEquals(floorPlan.getImage(), FloorPlanRequests.getFloorPlanByFloorId(floorPlan.getFloorId()).getImage());
        Assert.assertEquals(floorPlan.getFloorId(), FloorPlanRequests.getFloorPlanByFloorId(floorPlan.getFloorId()).getFloorId());
    }

    @Test
    public void testCreateFloorPlan() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FloorPlanRequests.createFloorPlan(floorPlan));

    }

    @Test
    public void testUpdateFloorPlan() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FloorPlanRequests.updateFloorPlan(floorPlan));
    }

    @Test
    public void testDeleteFloorPlan() throws Exception {
        String expectedResponse = "OK";
        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FloorPlanRequests.deleteFloorPlan(floorPlan.getId()));
    }

}
