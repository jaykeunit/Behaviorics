package behaviorics.httpRequests;

import behaviorics.models.WorkingStateImage;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class WorkingStateImageRequests extends HttpRequests {

    public static WorkingStateImage getWorkingStateImageById(int id) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "workingStateImages", Integer.toString(id)));
        return mapper.readValue(response, WorkingStateImage.class);
    }

    public static WorkingStateImage getWorkingStateImagesForCameraId(int cameraId) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "workingStateImages/cameraID", cameraId));
        return mapper.readValue(response, new TypeReference<WorkingStateImage>() {});
    }

    public static String createWorkingStateImage(WorkingStateImage image) throws IOException {
        String workingStateImageJson = mapper.writeValueAsString(image);
        return makePostRequest(String.format("%s/%s", restEndpoint, "workingStateImages"), workingStateImageJson);
    }

    public static String updateWorkingStateImage(WorkingStateImage image) throws IOException {
        String workingStateImageJson = mapper.writeValueAsString(image);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "workingStateImages", image.getId()), workingStateImageJson);

    }

    public static String deleteWorkingStateImage(int id) throws Exception {
        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "workingStateImages", Integer.toString(id)));

    }

}
