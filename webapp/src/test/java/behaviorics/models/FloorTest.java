package behaviorics.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class FloorTest {

    private Floor floor;

    @Before
    public void setUp() {
        floor = new Floor(1, 1, 1, "F", "MDA");
    }

    @Test
    public void testEmptyConstructor() {
        Floor floorBO = new Floor();
        assertEquals(0, floorBO.getID());
    }

    @Test
    public void testInitializeFloor() {
        Floor newFloor = new Floor(1, 1, 1, "F", "MDA");
        assertEquals(floor.getID(), newFloor.getID());
        assertEquals(floor.getFloorNumber(), newFloor.getFloorNumber());
        assertEquals(floor.getBuildingID(), newFloor.getBuildingID());
        assertEquals(floor.getFloorType(), newFloor.getFloorType());
        assertEquals(floor.getNickname(), newFloor.getNickname());
    }

    @Test
    public void testToString() {
        assertEquals("id: 1 buildingID: 1 floorNumber: 1 floorType: F nickname: MDA ",
                floor.toString());
    }
}