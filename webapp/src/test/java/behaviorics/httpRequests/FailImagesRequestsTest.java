package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.FailImage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, FailImagesRequests.class})
public class FailImagesRequestsTest extends HttpRequests{

    final FailImage image = new FailImage(1, null, 1);

    @Test
    public void testGetFailImageById() throws Exception {
        String expectedResponse = "{\"id\":" + image.getId()
                + ",\"image\":" + image.getImage()
                + ",\"repairID\":" + image.getRepairID() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(image.toString(), FailImagesRequests.getFailImageById(image.getId()).toString());

    }

    @Test
    public void testGetFailImageByRepairId() throws Exception {
        int repairId = 1;

        FailImage image1 = new FailImage(1, null, repairId);

        String expectedResponse = "{\"id\":" + image1.getId()
                + ",\"image\":" + image1.getImage()
                + ",\"repairID\":" + image1.getRepairID() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(image1.toString(), FailImagesRequests.getFailImageIdByRepairId(repairId).toString());

    }

    @Test
    public void testCreateFailImage() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FailImagesRequests.createFailImage(image));

    }

    @Test
    public void testUpdateFailImage() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FailImagesRequests.updateFailImage(image));

    }

    @Test
    public void testDeleteFailImageById() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, FailImagesRequests.deleteFailImagebyId(image.getId()));

    }

}
