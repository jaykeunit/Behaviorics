package behaviorics.httpRequests;

import behaviorics.models.Building;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class BuildingRequests extends HttpRequests {

    public static Building getBuildingByBuildingName(String name) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "building", name));
        return mapper.readValue(response, Building.class);
    }

    public static List<Building> getBuildingsByEntityId(int entityId) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "building/entityID", Integer.toString(entityId)));
        return mapper.readValue(response, new TypeReference<List<Building>>() {});
    }

    public static List<Building> getAllBuildingsByOrganizationID(int organizationID) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s/%s", restEndpoint, "building", "organizationID", Integer.toString(organizationID)));
        return mapper.readValue(response, new TypeReference<List<Building>>() {});
    }

    public static String createBuilding(Building building) throws IOException {
        int entityID = building.getEntityID();

        String buildingJson = mapper.writeValueAsString(building);
        return makePostRequest(String.format("%s/%s", restEndpoint, "building"), buildingJson);
    }

    public static String updateBuilding(Building building) throws IOException {

        String buildingJson = mapper.writeValueAsString(building);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "building", building.getId()), buildingJson);
    }

    public static String deleteBuildingById(int id) throws Exception {

        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "building", Integer.toString(id)));
    }

}
