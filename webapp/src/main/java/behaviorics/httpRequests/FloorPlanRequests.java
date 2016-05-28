package behaviorics.httpRequests;

import behaviorics.models.FloorPlan;
import java.io.IOException;

import static behaviorics.HTTPConnection.*;

public class FloorPlanRequests extends HttpRequests {


    public static FloorPlan getFloorPlanById(int id) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "floorPlan", id));
        return mapper.readValue(response, FloorPlan.class);
    }

    public static FloorPlan getFloorPlanByFloorId(int floorId) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "floorPlan/floor", floorId));
        return mapper.readValue(response, FloorPlan.class);
    }

    public static String createFloorPlan(FloorPlan floorPlan) throws IOException {
        String floorPlanJson = mapper.writeValueAsString(floorPlan);
        return makePostRequest(String.format("%s/%s", restEndpoint, "floorPlan"), floorPlanJson);
    }

    public static String updateFloorPlan(FloorPlan floorPlan) throws IOException {
        String floorPlanJson = mapper.writeValueAsString(floorPlan);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "floorPlan", floorPlan.getId()), floorPlanJson);
    }

    public static String deleteFloorPlan(int id) throws Exception {
        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "floorPlan", id));
    }

}
