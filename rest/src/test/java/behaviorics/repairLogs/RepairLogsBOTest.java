package behaviorics.repairLogs;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class RepairLogsBOTest {

    private RepairLogsBO repairLogsBO;

    @Before
    public void setUp() {
        repairLogsBO = new RepairLogsBO(1, 1, new Date(2016,3,28), "Working", "Water Damage", new Date(2016,3,28), new Date(2016,3,28));
    }

    @Test
    public void testEmptyConstructor() {
        RepairLogsBO repairLogsBo = new RepairLogsBO();
        assertEquals(0, repairLogsBo.getId());
    }

    @Test
    public void testInitialStateOfBuilding() {
        assertEquals(1, repairLogsBO.getId());
        assertEquals(1, repairLogsBO.getCameraID());
        assertEquals(new Date(2016,3,28), repairLogsBO.getDateFailed());
        assertEquals("Working", repairLogsBO.getRepairStatus());
        assertEquals("Water Damage", repairLogsBO.getFailReason());
        assertEquals(new Date(2016,3,28), repairLogsBO.getDateRepaired());
        assertEquals(new Date(2016,3,28), repairLogsBO.getRepairRequestDate());
    }

    @Test
    public void testToString() {
        String toStringTester = "RepairLogsBO{" +
                "id=" + 1 +
                ", cameraID=" + 1 +
                ", dateFailed=" + new Date(2016,3,28) +
                ", repairStatus='" + "Working" + '\'' +
                ", failReason='" + "Water Damage" + '\'' +
                ", dateRepaired=" + new Date(2016,3,28) +
                ", repairRequestDate=" + new Date(2016,3,28) +
                '}';
        assertEquals(toStringTester, repairLogsBO.toString());
    }

}
