package behaviorics.httpRequests;

import behaviorics.models.Camera;
import behaviorics.models.EditCamera;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

import static behaviorics.HTTPConnection.*;

public class CameraRequests extends HttpRequests{

    public static Camera getCameraByName(String  name) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "cameras", name));
        return mapper.readValue(response, Camera.class);
    }
    public static Camera getCameraById(int id) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "camera", Integer.toString(id)));
        return mapper.readValue(response, Camera.class);
    }

    public static List<Camera> getAllCameras() throws Exception {
        String response = makeGetRequest(String.format("%s/%s", restEndpoint, "cameras/all"));
        return mapper.readValue(response, new TypeReference<List<Camera>>() {});
    }

    public static List<Camera> getAllCamerasForFloorId(int floorId) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "cameras/floorID", floorId));
        return mapper.readValue(response, new TypeReference<List<Camera>>() {});
    }
    public static List<Camera> getAllCamerasByOrgId(int orgId) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "cameras/orgID", orgId));
        return mapper.readValue(response, new TypeReference<List<Camera>>() {});
    }

    public static List<EditCamera> getAllCamerasForEditPage(int organizationID) throws Exception {
        String response = makeGetRequest(String.format("%s/%s/%s", restEndpoint, "allCameras/organizationID", organizationID));
        return mapper.readValue(response, new TypeReference<List<EditCamera>>() {});
    }

    public static String createCamera(Camera camera) throws IOException {
        String cameraJson = mapper.writeValueAsString(camera);
        return makePostRequest(String.format("%s/%s", restEndpoint, "cameras"), cameraJson);
    }

    public static String updateCamera(Camera camera) throws IOException {
        String imageJson = mapper.writeValueAsString(camera);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "cameras", camera.getId()), imageJson);
    }

    public static String updateCameraForEdit(EditCamera editCamera) throws IOException {
        String editCameraJson = mapper.writeValueAsString(editCamera);
        return makePutRequest(String.format("%s/%s/%s", restEndpoint, "camerasEdit", editCamera.getId()), editCameraJson);
    }

    public static String deleteCameraById(int id) throws Exception {
        return makeDeleteRequest(String.format("%s/%s/%s", restEndpoint, "cameras", Integer.toString(id)));
    }

}
