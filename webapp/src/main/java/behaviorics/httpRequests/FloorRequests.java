package behaviorics.httpRequests;

import behaviorics.models.Floor;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class FloorRequests extends HttpRequests {

    public static Floor getFloor(int buildingId, int floorNumber) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s/%s", restEndpoint, "floor", buildingId, floorNumber));
        return mapper.readValue(response, Floor.class);
    }

    public static Floor getFloorById(int id) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s/%s", restEndpoint, "floors", "id", id));
        return mapper.readValue(response, Floor.class);
    }

    public static List<Floor> getFloorsByBuildingId(int buildingId) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "floors", Integer.toString(buildingId)));
        return mapper.readValue(response, new TypeReference<List<Floor>>() {});
    }

    public static List<Floor> getAllFloorsForAnOrganization(int organizationID) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s/%s", restEndpoint, "floors", "organizationID", Integer.toString(organizationID)));
        return mapper.readValue(response, new TypeReference<List<Floor>>() {});
    }

    public static String createFloor(Floor floor) throws IOException {

        String floorJson = mapper.writeValueAsString(floor);
        return makePostRequest(String.format("%s/%s", restEndpoint, "floor"), floorJson);
    }

    public static String updateFloorById(Floor floor) throws IOException {

        String floorJson = mapper.writeValueAsString(floor);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "floor", floor.getID()), floorJson);
    }

    public static String deleteFloorById(int id) throws Exception {

        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "floor", Integer.toString(id)));
    }

}
