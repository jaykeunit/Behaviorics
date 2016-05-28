package behaviorics.models;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.*;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;


public class FloorPlanTest {


    private FloorPlan floorPlan;

    @Before
    public void setUp() {
        byte[] image = new byte[10];
        floorPlan = new FloorPlan(1, image, 1);
    }

    @Test
    public void testEmptyConstructor() {
        FloorPlan floorPlan = new FloorPlan();
        assertEquals(0, floorPlan.getId());
    }

    @Test
    public void testInitialStateOfBuilding() {
        byte[] image = new byte[10];
        assertEquals(1, floorPlan.getId());
        assertArrayEquals(image, floorPlan.getImage());
        assertEquals(1, floorPlan.getFloorId());

    }

    @Test
    public void testToString() {
        String toStringTester = "id: 1, image: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], floorId: 1";
        assertEquals(toStringTester, floorPlan.toString());
    }
}