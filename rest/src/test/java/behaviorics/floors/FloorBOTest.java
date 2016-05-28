package behaviorics.floors;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FloorBOTest {

    private FloorBO floorBO;

    @Before
    public void setUp() {
        floorBO = new FloorBO(1, 1, 1, "F", "F1");
    }

    @Test
    public void testEmptyConstructor() {
        FloorBO floorBO = new FloorBO();
        assertEquals(0, floorBO.getID());
    }

    @Test
    public void testInitializeFloor() {
        FloorBO newFloor = new FloorBO(1, 1, 1, "F", "F1");
        assertEquals(floorBO.getID(), newFloor.getID());
        assertEquals(floorBO.getFloorNumber(), newFloor.getFloorNumber());
        assertEquals(floorBO.getBuildingID(), newFloor.getBuildingID());
        assertEquals(floorBO.getFloorType(), newFloor.getFloorType());
        assertEquals(floorBO.getNickname(), newFloor.getNickname());
    }

    @Test
    public void testInitializeFloorWithBuildingName() {
        String buildingName = "Research";

        floorBO.setBuildingName(buildingName);

        FloorBO newFloor = new FloorBO(1, 1, buildingName, 1, "F", "F1");
        assertEquals(floorBO.getID(), newFloor.getID());
        assertEquals(floorBO.getFloorNumber(), newFloor.getFloorNumber());
        assertEquals(floorBO.getBuildingID(), newFloor.getBuildingID());
        assertEquals(floorBO.getFloorType(), newFloor.getFloorType());
        assertEquals(floorBO.getNickname(), newFloor.getNickname());
        assertEquals(floorBO.getBuildingName(), newFloor.getBuildingName());
    }

    @Test
    public void testSetBuildingName() {
        FloorBO newFloor = new FloorBO(1, 1, 1, "F", "F1");
        String buildingName = "Research";
        newFloor.setBuildingName(buildingName);
        floorBO.setBuildingName(buildingName);

        assertEquals(floorBO.getBuildingName(), newFloor.getBuildingName());
    }

    @Test
    public void testToString() {
        assertEquals("id: 1 buildingID: 1 floorNumber: 1 floorType: F nickname: F1 ",
                floorBO.toString());
    }
}
