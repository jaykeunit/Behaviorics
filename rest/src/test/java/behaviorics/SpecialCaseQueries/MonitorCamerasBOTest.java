package behaviorics.SpecialCaseQueries;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MonitorCamerasBOTest {

    private MonitorCamerasBO monitorCamerasBO;

    @Before
    public void setUp(){
        monitorCamerasBO = new MonitorCamerasBO(1, 1, "UH Main", 1,"Agnes Arnold Hall", 1, 1);
    }

    @Test
    public void testEmptyConstructor() {
        MonitorCamerasBO monitorCamerasBO = new MonitorCamerasBO();
        assertEquals(0, monitorCamerasBO.getOrganizationID());
    }

    @Test
    public void testInitialStateOfMonitorCameras(){
        assertEquals(1, monitorCamerasBO.getOrganizationID());
        assertEquals(1, monitorCamerasBO.getEntityID());
        assertEquals("UH Main", monitorCamerasBO.getEntityName());
        assertEquals(1, monitorCamerasBO.getBuildingID());
        assertEquals("Agnes Arnold Hall", monitorCamerasBO.getBuildingName());
        assertEquals(1, monitorCamerasBO.getFloorID());
        assertEquals(1, monitorCamerasBO.getFloorNumber());
    }
}


