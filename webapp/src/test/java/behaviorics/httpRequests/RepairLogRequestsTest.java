package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.RepairLog;
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
@PrepareForTest({HTTPConnection.class, RepairLogRequests.class})
public class RepairLogRequestsTest extends HttpRequests{

    final RepairLog log = new RepairLog(1, 1, new Date(123), "pending", "abc", new Date(123), new Date(123));

    @Test
    public void testGetRepairLogById() throws Exception {

        String expectedResponse = "{\"id\":" + log.getId()
                + ",\"cameraID\":" + log.getCameraID()
                //+ ",\"dateFailed\":" + log.getDateFailed()
                + ",\"repairStatus\":\"" + log.getRepairStatus()
                + "\",\"failReason\":\"" + log.getFailReason() + "\"}";
                //+ "\",\"dateRepaired\":" + log.getDateRepaired()
                //+ ",\"repairRequestDate\":" + log.getRepairRequestDate() + "}";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(log.toString(), RepairLogRequests.getRepairLogById(log.getId()).toString());
    }

    @Test
    public void testGetRepairLogsByCameraId() throws Exception {
        int cameraId = 1;
        RepairLog log1 = new RepairLog(1, cameraId, new Date(2), "pending", "abc", new Date(2), new Date(2));
        RepairLog log2 = new RepairLog(2, cameraId, new Date(1), "pending", "abc", new Date(1), new Date(1));

        List<RepairLog> expectedList = new ArrayList<RepairLog>() {{
            add(log1);
            add(log2);
        }};

        String expectedResponse = "[{\"id\":" + log1.getId()
                + ",\"cameraID\":" + log1.getCameraID()
                //+ ",\"dateFailed\":" + log1.getDateFailed()
                + ",\"repairStatus\":\"" + log1.getRepairStatus()
                + "\",\"failReason\":\"" + log1.getFailReason() + "\"},"
                //+ "\",\"dateRepaired\":" + log1.getDateRepaired()
                //+ ",\"repairRequestDate\":" + log1.getRepairRequestDate() + "},"
                + "{\"id\":" + log2.getId()
                + ",\"cameraID\":" + log2.getCameraID()
                //+ ",\"dateFailed\":" + log2.getDateFailed()
                + ",\"repairStatus\":\"" + log2.getRepairStatus()
                + "\",\"failReason\":\"" + log2.getFailReason() + "\"}]";
                //+ "\",\"dateRepaired\":" + log2.getDateRepaired()
                //+ ",\"repairRequestDate\":" + log2.getRepairRequestDate() + "}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), RepairLogRequests.getRepairLogsByCameraId(cameraId).toString());

    }

    @Test
    public void testGetActiveRepairs() throws Exception {

        RepairLog log1 = new RepairLog(1, 1, new Date(2), "pending", "abc", new Date(2), new Date(2));
        RepairLog log2 = new RepairLog(2, 2, new Date(1), "pending", "abc", new Date(1), new Date(1));

        List<RepairLog> expectedList = new ArrayList<RepairLog>() {{
            add(log1);
            add(log2);
        }};

        String expectedResponse = "[{\"id\":" + log1.getId()
                + ",\"cameraID\":" + log1.getCameraID()
                //+ ",\"dateFailed\":" + log1.getDateFailed()
                + ",\"repairStatus\":\"" + log1.getRepairStatus()
                + "\",\"failReason\":\"" + log1.getFailReason() + "\"},"
                //+ "\",\"dateRepaired\":" + log1.getDateRepaired()
                //+ ",\"repairRequestDate\":" + log1.getRepairRequestDate() + "},"
                + "{\"id\":" + log2.getId()
                + ",\"cameraID\":" + log2.getCameraID()
                //+ ",\"dateFailed\":" + log2.getDateFailed()
                + ",\"repairStatus\":\"" + log2.getRepairStatus()
                + "\",\"failReason\":\"" + log2.getFailReason() + "\"}]";
        //+ "\",\"dateRepaired\":" + log2.getDateRepaired()
        //+ ",\"repairRequestDate\":" + log2.getRepairRequestDate() + "}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), RepairLogRequests.getActiveRepairs().toString());

    }

    @Test
    public void testCreateRepairLog() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePostRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, RepairLogRequests.createRepairLog(log));

    }

    @Test
    public void testUpdateRepairLog() throws IOException {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makePutRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, RepairLogRequests.updateRepairLog(log));

    }

    @Test
    public void testDeleteRepairLog() throws Exception {
        String expectedResponse = "OK";

        PowerMockito.stub(method(HTTPConnection.class, "makeDeleteRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedResponse, RepairLogRequests.deleteRepairLog(log.getId()));

    }
}
