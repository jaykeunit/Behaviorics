package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.Building;
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
@PrepareForTest({HTTPConnection.class, BuildingRequests.class})
public class BuildingRequestsTest {

    final Building building = new Building(1, "Library", 2, 1111, "Abc Dr", "Houston", 77004, "LIB", "TX");

    @Test
    public void testGetBuildingsByBuildingName() throws Exception {



        String expectedResponse = "{\"id\":" + building.getId()
                                + ",\"buildingName\":\"" + building.getBuildingName()
                                + "\",\"entityID\":" + building.getEntityID()
                                + ",\"streetCode\":" + building.getStreetCode()
                                + ",\"streetName\":\"" + building.getStreetName()
                                + "\",\"city\":\"" + building.getCity()
                                + "\",\"zipcode\":" + building.getZipcode()
                                + ",\"buildingAcronym\":\"" + building.getBuildingAcronym()
                                + "\",\"state\":\"" + building.getState() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(building.getId(), BuildingRequests.getBuildingByBuildingName("Library").getId());
        Assert.assertEquals(building.getBuildingName(), BuildingRequests.getBuildingByBuildingName("Library").getBuildingName());
        Assert.assertEquals(building.getEntityID(), BuildingRequests.getBuildingByBuildingName("Library").getEntityID());
        Assert.assertEquals(building.getStreetCode(), BuildingRequests.getBuildingByBuildingName("Library").getStreetCode());
        Assert.assertEquals(building.getStreetName(), BuildingRequests.getBuildingByBuildingName("Library").getStreetName());
        Assert.assertEquals(building.getCity(), BuildingRequests.getBuildingByBuildingName("Library").getCity());
        Assert.assertEquals(building.getZipcode(), BuildingRequests.getBuildingByBuildingName("Library").getZipcode());
        Assert.assertEquals(building.getBuildingAcronym(), BuildingRequests.getBuildingByBuildingName("Library").getBuildingAcronym());
        Assert.assertEquals(building.getState(), BuildingRequests.getBuildingByBuildingName("Library").getState());
    }

    @Test
    public void testGetBuildingsByEntityId() throws Exception {

        int entityId = 2;

        Building building1 = new Building(1, "Library", entityId, 1111, "Abc Dr", "Houston", 77004, "LIB", "TX");
        Building building2 = new Building(2, "Library", entityId, 1111, "Abc Dr", "Houston", 77004, "LIB", "TX");

        List<Building> expectedList = new ArrayList<Building>() {{
            add(building1);
            add(building2);
        }};

        String expectedResponse = "[{\"id\":" + building1.getId()
                + ",\"buildingName\":\"" + building1.getBuildingName()
                + "\",\"entityID\":" + building1.getEntityID()
                + ",\"streetCode\":" + building1.getStreetCode()
                + ",\"streetName\":\"" + building1.getStreetName()
                + "\",\"city\":\"" + building1.getCity()
                + "\",\"zipcode\":" + building1.getZipcode()
                + ",\"buildingAcronym\":\"" + building1.getBuildingAcronym()
                + "\",\"state\":\"" + building1.getState() + "\"},"
                + "{\"id\":" + building2.getId()
                + ",\"buildingName\":\"" + building2.getBuildingName()
                + "\",\"entityID\":" + building2.getEntityID()
                + ",\"streetCode\":" + building2.getStreetCode()
                + ",\"streetName\":\"" + building2.getStreetName()
                + "\",\"city\":\"" + building2.getCity()
                + "\",\"zipcode\":" + building2.getZipcode()
                + ",\"buildingAcronym\":\"" + building2.getBuildingAcronym()
                + "\",\"state\":\"" + building2.getState() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), BuildingRequests.getBuildingsByEntityId(entityId).toString());
    }

    @Test
    public void testGetAllBuildingsByOrganizationID() throws Exception{

        int organizationID = 1;

        Building building1 = new Building(1, "Library", 1, 1111, "Abc Dr", "Houston", 77004, "LIB", "TX");
        Building building2 = new Building(2, "Library", 2, 1111, "Abc Dr", "Houston", 77004, "LIB", "TX");

        List<Building> expectedList = new ArrayList<Building>() {{
            add(building1);
            add(building2);
        }};

        String expectedResponse = "[{\"id\":" + building1.getId()
                + ",\"buildingName\":\"" + building1.getBuildingName()
                + "\",\"entityID\":" + building1.getEntityID()
                + ",\"streetCode\":" + building1.getStreetCode()
                + ",\"streetName\":\"" + building1.getStreetName()
                + "\",\"city\":\"" + building1.getCity()
                + "\",\"zipcode\":" + building1.getZipcode()
                + ",\"buildingAcronym\":\"" + building1.getBuildingAcronym()
                + "\",\"state\":\"" + building1.getState() + "\"},"
                + "{\"id\":" + building2.getId()
                + ",\"buildingName\":\"" + building2.getBuildingName()
                + "\",\"entityID\":" + building2.getEntityID()
                + ",\"streetCode\":" + building2.getStreetCode()
                + ",\"streetName\":\"" + building2.getStreetName()
                + "\",\"city\":\"" + building2.getCity()
                + "\",\"zipcode\":" + building2.getZipcode()
                + ",\"buildingAcronym\":\"" + building2.getBuildingAcronym()
                + "\",\"state\":\"" + building2.getState() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), BuildingRequests.getAllBuildingsByOrganizationID(organizationID).toString());

    }

    @Test
    public void testCreateBuilding() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, BuildingRequests.createBuilding(building));
    }

    @Test
    public void testUpdateBuildingById() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, BuildingRequests.updateBuilding(building));
    }

    @Test
    public void testDeleteBuildingById() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, BuildingRequests.deleteBuildingById(building.getId()));
    }
}
