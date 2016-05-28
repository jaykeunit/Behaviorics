package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.MonitorCameras;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, MonitorCamerasPageRequest.class})
public class MonitorCamerasPageRequestTest {

    @Test
    public void testGetOrganizationToFloorsList() throws Exception {

        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "UH Main", 4, "Technology Building", 94, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "UH Main", 2, "Science&Research2", 104, 3);

        List<MonitorCameras> expectedList = new ArrayList<MonitorCameras>() {{
            add(monitorCameras1);
            add(monitorCameras2);
        }};

        String expectedResponse = "[{\"organizationID\":" + monitorCameras1.getOrganizationID()
                + ",\"entityID\":" + monitorCameras1.getEntityID()
                + ",\"entityName\":\"" + monitorCameras1.getEntityName()
                + "\",\"buildingID\":" + monitorCameras1.getBuildingID()
                + ",\"buildingName\":\"" + monitorCameras1.getBuildingName()
                + "\",\"floorID\":" + monitorCameras1.getFloorID()
                + ",\"floorNumber\":" + monitorCameras1.getFloorNumber() + "},"
                + "{\"organizationID\":" + monitorCameras2.getOrganizationID()
                + ",\"entityID\":" + monitorCameras2.getEntityID()
                + ",\"entityName\":\"" + monitorCameras2.getEntityName()
                + "\",\"buildingID\":" + monitorCameras2.getBuildingID()
                + ",\"buildingName\":\"" + monitorCameras2.getBuildingName()
                + "\",\"floorID\":" + monitorCameras2.getFloorID()
                + ",\"floorNumber\":" + monitorCameras2.getFloorNumber() + "}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), MonitorCamerasPageRequest.getOrganizationToFloorsList().toString());
    }
}
