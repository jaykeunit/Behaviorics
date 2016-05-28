package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.Building;
import behaviorics.models.Floor;
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
@PrepareForTest({HTTPConnection.class, FloorRequests.class})
public class FloorRequestsTest {

    final Floor floor = new Floor(1, 2, 1, "", "F1");
    @Test
    public void testGetFloor() throws Exception {
        String expectedResponse = "{\"id\":" + floor.getID()
                + ",\"buildingID\":" + floor.getBuildingID()
                + ",\"floorNumber\":" + floor.getFloorNumber()
                + ",\"floorType\":\"" + floor.getFloorType()
                + "\",\"nickname\":\"" + floor.getNickname() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(floor.getID(), FloorRequests.getFloor(floor.getBuildingID(), floor.getFloorNumber()).getID());
        Assert.assertEquals(floor.getBuildingID(), FloorRequests.getFloor(floor.getBuildingID(), floor.getFloorNumber()).getBuildingID());
        Assert.assertEquals(floor.getFloorNumber(), FloorRequests.getFloor(floor.getBuildingID(), floor.getFloorNumber()).getFloorNumber());
        Assert.assertEquals(floor.getFloorType(), FloorRequests.getFloor(floor.getBuildingID(), floor.getFloorNumber()).getFloorType());
        Assert.assertEquals(floor.getNickname(), FloorRequests.getFloor(floor.getBuildingID(), floor.getFloorNumber()).getNickname());
    }

    @Test
    public void testGetFloorById() throws Exception {
        String expectedResponse = "{\"id\":" + floor.getID()
                + ",\"buildingID\":" + floor.getBuildingID()
                + ",\"floorNumber\":" + floor.getFloorNumber()
                + ",\"floorType\":\"" + floor.getFloorType()
                + "\",\"nickname\":\"" + floor.getNickname() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(floor.toString(), FloorRequests.getFloorById(floor.getID()).toString());
    }

    @Test
    public void testGetFloorsByBuildingId() throws Exception {
        int buildingId = 2;
        Floor floor1 = new Floor(1, buildingId, 1, "", "F1");
        Floor floor2 = new Floor(2, buildingId, 1, "", "F1");

        List<Floor> expectedList = new ArrayList<Floor>() {{
            add(floor1);
            add(floor2);
        }};

        String expectedResponse = "[{\"id\":" + floor1.getID()
                + ",\"buildingID\":" + floor1.getBuildingID()
                + ",\"floorNumber\":" + floor1.getFloorNumber()
                + ",\"floorType\":\"" + floor1.getFloorType()
                + "\",\"nickname\":\"" + floor1.getNickname() + "\"},"
                + "{\"id\":" + floor2.getID()
                + ",\"buildingID\":" + floor2.getBuildingID()
                + ",\"floorNumber\":" + floor2.getFloorNumber()
                + ",\"floorType\":\"" + floor2.getFloorType()
                + "\",\"nickname\":\"" + floor2.getNickname() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), FloorRequests.getFloorsByBuildingId(buildingId).toString());
    }

    @Test
    public void testGetAllFloorsForAnOrganization() throws Exception {
        int organizationID = 2;
        Floor floor1 = new Floor(1, 1, 1, "", "F1");
        Floor floor2 = new Floor(2, 2, 1, "", "F1");

        List<Floor> expectedList = new ArrayList<Floor>() {{
            add(floor1);
            add(floor2);
        }};

        String expectedResponse = "[{\"id\":" + floor1.getID()
                + ",\"buildingID\":" + floor1.getBuildingID()
                + ",\"floorNumber\":" + floor1.getFloorNumber()
                + ",\"floorType\":\"" + floor1.getFloorType()
                + "\",\"nickname\":\"" + floor1.getNickname() + "\"},"
                + "{\"id\":" + floor2.getID()
                + ",\"buildingID\":" + floor2.getBuildingID()
                + ",\"floorNumber\":" + floor2.getFloorNumber()
                + ",\"floorType\":\"" + floor2.getFloorType()
                + "\",\"nickname\":\"" + floor2.getNickname() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), FloorRequests.getAllFloorsForAnOrganization(organizationID).toString());
    }

    @Test
    public void testCreateFloor() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FloorRequests.createFloor(floor));

    }

    @Test
    public void testUpdateFloorById() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FloorRequests.updateFloorById(floor));
    }

    @Test
    public void testDeleteFloorById() throws Exception {
        String expectedResponse = "OK";
        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FloorRequests.deleteFloorById(floor.getID()));
    }

}
