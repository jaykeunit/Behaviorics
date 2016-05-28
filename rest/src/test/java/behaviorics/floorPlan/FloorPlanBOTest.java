package behaviorics.floorPlan;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

import static org.junit.Assert.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class FloorPlanBOTest {

    private FloorPlanBO floorPlanBO;

    @Before
    public void setUp() {
        byte[] image = new byte[10];
        floorPlanBO = new FloorPlanBO(1, image, 1);
    }

    @Test
    public void testEmptyConstructor() {
        FloorPlanBO floorPlanBO = new FloorPlanBO();
        assertEquals(0, floorPlanBO.getId());
    }

    @Test
    public void testConstructorWithBase64Decoder(){
        byte[] image = new byte[10];
        String base64Encrypted = DatatypeConverter.printBase64Binary(image);
        floorPlanBO = new FloorPlanBO(1, base64Encrypted, 1);

        assertArrayEquals(image, floorPlanBO.getImage());
    }

    @Test
    public void testInitialStateOfFloorPlan() {
        byte[] image = new byte[10];
        assertEquals(1, floorPlanBO.getId());
        assertArrayEquals(image, floorPlanBO.getImage());
        assertEquals(1, floorPlanBO.getFloorId());

    }

    @Test
    public void testToString() {
        String toStringTester = "id: 1, image: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0], floorId: 1";
        assertEquals(toStringTester, floorPlanBO.toString());
    }
}
