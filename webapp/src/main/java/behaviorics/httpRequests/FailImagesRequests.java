package behaviorics.httpRequests;

import behaviorics.models.FailImage;
import behaviorics.models.RepairLog;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class FailImagesRequests extends HttpRequests{

    public static FailImage getFailImageById(int id) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "failImages", id));
        return mapper.readValue(response, FailImage.class);
    }

    public static FailImage getFailImageIdByRepairId(int repairId) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "failImages/repairID", repairId));
        return mapper.readValue(response, new TypeReference<FailImage>() {});
    }

    public static String createFailImage(FailImage image) throws IOException {
        String imageJson = mapper.writeValueAsString(image);
        return makePostRequest(String.format("%s/%s", restEndpoint, "failImages"), imageJson);
    }

    public static String updateFailImage(FailImage image) throws IOException {
        String imageJson = mapper.writeValueAsString(image);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "failImages", image.getId()), imageJson);

    }

    public static String deleteFailImagebyId(int id) throws Exception {
        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "failImages", Integer.toString(id)));

    }
}
