package behaviorics.httpRequests;

import behaviorics.models.MonitorCameras;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import static behaviorics.HTTPConnection.makeGetRequest;

public class MonitorCamerasPageRequest extends HttpRequests{

    public static List<MonitorCameras> getOrganizationToFloorsList() throws Exception {

        String response = makeGetRequest(String.format("%s/%s", restEndpoint, "OrganizationToFloorsList"));
        return mapper.readValue(response, new TypeReference<List<MonitorCameras>>() {});
    }
}