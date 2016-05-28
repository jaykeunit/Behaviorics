package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.Building;
import behaviorics.models.Camera;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, CameraRequests.class})
public class CameraRequestsTest extends HttpRequests{

    final Camera camera = new Camera(1, "NotARealCamera", "123", "username", "password", 1, 1, new Date(0), 1, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);

    @Test
    public void testGetCameraByName() throws Exception {
        String expectedResponse = "{\"id\":" + camera.getId()
                + ",\"cameraName\":\"" + camera.getCameraName()
                + "\",\"feedIP\":\"" + camera.getFeedIP()
                + "\",\"feedCredential\":\"" + camera.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera.getFeedPassword()
                + "\",\"locationX\":" + camera.getLocationX()
                + ",\"locationY\":" + camera.getLocationY()
                //+ ",\"dateInstalled\":" + "\""+camera.getDateInstalled() + "\""
                + ",\"floorID\":" + camera.getFloorID()
                + ",\"vendorName\":\"" + camera.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera.getCameraStatus()
                + "\",\"httpPort\":\"" + camera.getHttpPort()
                + "\",\"onvifPort\":\"" + camera.getOnvifPort()
                + "\",\"orgId\":\"" + camera.getOrgId() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(camera.toString(), CameraRequests.getCameraByName(camera.getCameraName()).toString());
    }

    @Test
    public void testGetCameraByCameraID() throws Exception {
        String expectedResponse = "{\"id\":" + camera.getId()
                + ",\"cameraName\":\"" + camera.getCameraName()
                + "\",\"feedIP\":\"" + camera.getFeedIP()
                + "\",\"feedCredential\":\"" + camera.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera.getFeedPassword()
                + "\",\"locationX\":" + camera.getLocationX()
                + ",\"locationY\":" + camera.getLocationY()
                //+ ",\"dateInstalled\":" + "\""+camera.getDateInstalled() + "\""
                + ",\"floorID\":" + camera.getFloorID()
                + ",\"vendorName\":\"" + camera.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera.getCameraStatus()
                + "\",\"httpPort\":\"" + camera.getHttpPort()
                + "\",\"onvifPort\":\"" + camera.getOnvifPort()
                + "\",\"orgId\":\"" + camera.getOrgId() + "\"}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(camera.toString(), CameraRequests.getCameraById(camera.getId()).toString());
    }

    @Test
    public void testGetAllCamerasByOrgId() throws Exception {
        int organizationId = 1;
        Camera camera1 = new Camera(1, "testCamera1", "123", "username", "password", 1, 1, new Date(1), 1, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera2", "123", "username", "password", 1, 1, new Date(1), 2, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);

        List<Camera> expectedList = new ArrayList<Camera>() {{
            add(camera1);
            add(camera2);
        }};

        String expectedResponse = "[{\"id\":" + camera1.getId()
                + ",\"cameraName\":\"" + camera1.getCameraName()
                + "\",\"feedIP\":\"" + camera1.getFeedIP()
                + "\",\"feedCredential\":\"" + camera1.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera1.getFeedPassword()
                + "\",\"locationX\":" + camera1.getLocationX()
                + ",\"locationY\":" + camera1.getLocationY()
                //+ ",\"dateInstalled\":" + "\"" + camera1.getDateInstalled() + "\""
                + ",\"floorID\":" + camera1.getFloorID()
                + ",\"vendorName\":\"" + camera1.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera1.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera1.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera1.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera1.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera1.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera1.getCameraStatus()
                + "\",\"httpPort\":\"" + camera1.getHttpPort()
                + "\",\"onvifPort\":\"" + camera1.getOnvifPort()
                + "\",\"orgId\":\"" + camera1.getOrgId() + "\"},"
                + "{\"id\":" + camera2.getId()
                + ",\"cameraName\":\"" + camera2.getCameraName()
                + "\",\"feedIP\":\"" + camera2.getFeedIP()
                + "\",\"feedCredential\":\"" + camera2.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera2.getFeedPassword()
                + "\",\"locationX\":" + camera2.getLocationX()
                + ",\"locationY\":" + camera2.getLocationY()
                //+ ",\"dateInstalled\":" + "\"" + camera2.getDateInstalled() + "\""
                + ",\"floorID\":" + camera2.getFloorID()
                + ",\"vendorName\":\"" + camera2.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera2.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera2.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera2.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera2.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera2.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera2.getCameraStatus()
                + "\",\"httpPort\":\"" + camera2.getHttpPort()
                + "\",\"onvifPort\":\"" + camera2.getOnvifPort()
                + "\",\"orgId\":\"" + camera2.getOrgId() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), CameraRequests.getAllCamerasByOrgId(organizationId).toString());

    }

    @Test
    public void testGetAllCamerasForFloorId() throws Exception {
        int floorId = 1;
        Camera camera1 = new Camera(1, "testCamera1", "123", "username", "password", 1, 1, new Date(1), floorId, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera2", "123", "username", "password", 1, 1, new Date(1), floorId, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);

        List<Camera> expectedList = new ArrayList<Camera>() {{
            add(camera1);
            add(camera2);
        }};

        String expectedResponse = "[{\"id\":" + camera1.getId()
                + ",\"cameraName\":\"" + camera1.getCameraName()
                + "\",\"feedIP\":\"" + camera1.getFeedIP()
                + "\",\"feedCredential\":\"" + camera1.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera1.getFeedPassword()
                + "\",\"locationX\":" + camera1.getLocationX()
                + ",\"locationY\":" + camera1.getLocationY()
                //+ ",\"dateInstalled\":" + "\"" + camera1.getDateInstalled() + "\""
                + ",\"floorID\":" + camera1.getFloorID()
                + ",\"vendorName\":\"" + camera1.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera1.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera1.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera1.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera1.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera1.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera1.getCameraStatus()
                + "\",\"httpPort\":\"" + camera1.getHttpPort()
                + "\",\"onvifPort\":\"" + camera1.getOnvifPort()
                + "\",\"orgId\":\"" + camera1.getOrgId() + "\"},"
                + "{\"id\":" + camera2.getId()
                + ",\"cameraName\":\"" + camera2.getCameraName()
                + "\",\"feedIP\":\"" + camera2.getFeedIP()
                + "\",\"feedCredential\":\"" + camera2.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera2.getFeedPassword()
                + "\",\"locationX\":" + camera2.getLocationX()
                + ",\"locationY\":" + camera2.getLocationY()
                //+ ",\"dateInstalled\":" + "\"" + camera2.getDateInstalled() + "\""
                + ",\"floorID\":" + camera2.getFloorID()
                + ",\"vendorName\":\"" + camera2.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera2.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera2.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera2.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera2.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera2.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera2.getCameraStatus()
                + "\",\"httpPort\":\"" + camera2.getHttpPort()
                + "\",\"onvifPort\":\"" + camera2.getOnvifPort()
                + "\",\"orgId\":\"" + camera2.getOrgId() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), CameraRequests.getAllCamerasForFloorId(floorId).toString());

    }

    @Test
    public void testGetAllCameras() throws Exception {

        Camera camera1 = new Camera(1, "testCamera1", "123", "username", "password", 1, 1, new Date(1), 1, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera2", "123", "username", "password", 1, 1, new Date(1), 2, "testVendor", "name", "123", "123", "email", "expiration", "pending", 88, 888, 1);

        List<Camera> expectedList = new ArrayList<Camera>() {{
            add(camera1);
            add(camera2);
        }};

        String expectedResponse = "[{\"id\":" + camera1.getId()
                + ",\"cameraName\":\"" + camera1.getCameraName()
                + "\",\"feedIP\":\"" + camera1.getFeedIP()
                + "\",\"feedCredential\":\"" + camera1.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera1.getFeedPassword()
                + "\",\"locationX\":" + camera1.getLocationX()
                + ",\"locationY\":" + camera1.getLocationY()
                //+ ",\"dateInstalled\":" + "\"" + camera1.getDateInstalled() + "\""
                + ",\"floorID\":" + camera1.getFloorID()
                + ",\"vendorName\":\"" + camera1.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera1.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera1.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera1.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera1.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera1.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera1.getCameraStatus()
                + "\",\"httpPort\":\"" + camera1.getHttpPort()
                + "\",\"onvifPort\":\"" + camera1.getOnvifPort()
                + "\",\"orgId\":\"" + camera1.getOrgId() + "\"},"
                + "{\"id\":" + camera2.getId()
                + ",\"cameraName\":\"" + camera2.getCameraName()
                + "\",\"feedIP\":\"" + camera2.getFeedIP()
                + "\",\"feedCredential\":\"" + camera2.getFeedCredential()
                + "\",\"feedPassword\":\"" + camera2.getFeedPassword()
                + "\",\"locationX\":" + camera2.getLocationX()
                + ",\"locationY\":" + camera2.getLocationY()
                //+ ",\"dateInstalled\":" + "\"" + camera2.getDateInstalled() + "\""
                + ",\"floorID\":" + camera2.getFloorID()
                + ",\"vendorName\":\"" + camera2.getVendorName()
                + "\",\"localMaintenanceName\":\"" + camera2.getLocalMaintenanceName()
                + "\",\"localMaintenanceNumber\":\"" + camera2.getLocalMaintenanceNumber()
                + "\",\"vendorPhoneNumber\":\"" + camera2.getVendorPhoneNumber()
                + "\",\"vendorEmail\":\"" + camera2.getVendorEmail()
                + "\",\"warrantyExpiration\":\"" + camera2.getWarrantyExpiration()
                + "\",\"cameraStatus\":\"" + camera2.getCameraStatus()
                + "\",\"httpPort\":\"" + camera2.getHttpPort()
                + "\",\"onvifPort\":\"" + camera2.getOnvifPort()
                + "\",\"orgId\":\"" + camera2.getOrgId() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), CameraRequests.getAllCameras().toString());

    }

    @Test
    public void testCreateCamera() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, CameraRequests.createCamera(camera));

    }

    @Test
    public void testUpdateCamera() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, CameraRequests.updateCamera(camera));

    }

    @Test
    public void testDeleteCameraById() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, CameraRequests.deleteCameraById(camera.getId()));

    }
}
