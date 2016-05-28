package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.Camera;
import behaviorics.models.WorkingStateImage;
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
@PrepareForTest({HTTPConnection.class, WorkingStateImageRequests.class})
public class WorkingStateImageRequestsTest extends HttpRequests{

    final WorkingStateImage image = new WorkingStateImage(1, null, 1);

    @Test
    public void testGetWorkingStateImageById() throws Exception {
        String expectedResponse = "{\"id\":" + image.getId()
                + ",\"image\":" + image.getImage()
                + ",\"cameraID\":" + image.getCameraID() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(image.toString(), WorkingStateImageRequests.getWorkingStateImageById(image.getId()).toString());

    }

    @Test
    public void testGetWorkingStateImagesForCameraId() throws Exception {
        int cameraId = 1;
        WorkingStateImage image1 = new WorkingStateImage(1, null, cameraId);

        String expectedResponse = "{\"id\":" + image1.getId()
                + ",\"image\":" + image1.getImage()
                + ",\"cameraID\":" + image1.getCameraID() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(image1.toString(), WorkingStateImageRequests.getWorkingStateImagesForCameraId(cameraId).toString());

    }

    @Test
    public void testCreateWorkingStateImages() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, WorkingStateImageRequests.createWorkingStateImage(image));

    }

    @Test
    public void testUpdateWorkingStateImage() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, WorkingStateImageRequests.updateWorkingStateImage(image));

    }

    @Test
    public void testDeleteWorkingStateImage() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, WorkingStateImageRequests.deleteWorkingStateImage(image.getId()));

    }
}
