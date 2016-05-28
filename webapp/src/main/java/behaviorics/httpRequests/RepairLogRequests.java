package behaviorics.httpRequests;

import behaviorics.models.RepairLog;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class RepairLogRequests extends HttpRequests {

    public static List<RepairLog> getActiveRepairs() throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "repairLogs", "activeRepairs"));
        return mapper.readValue(response, new TypeReference<List<RepairLog>>() {});
    }

    public static RepairLog getRepairLogById(int id) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "repairLogs", Integer.toString(id)));
        return mapper.readValue(response, RepairLog.class);
    }

    public static List<RepairLog> getRepairLogsByCameraId(int cameraId) throws Exception {

        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "repairLogs/cameraID", Integer.toString(cameraId)));
        return mapper.readValue(response, new TypeReference<List<RepairLog>>() {});
    }

    public static String createRepairLog(RepairLog log) throws IOException {

        String logJson = mapper.writeValueAsString(log);
        return makePostRequest(String.format("%s/%s", restEndpoint, "repairLogs"), logJson);
    }

    public static String updateRepairLog(RepairLog log) throws IOException {

        String logJson = mapper.writeValueAsString(log);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "repairLogs", log.getId()), logJson);
    }

    public static String deleteRepairLog(int id) throws Exception {

        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "repairLogs", Integer.toString(id)));
    }
}
