package behaviorics.failImages;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class FailImagesBOTest {

    private FailImagesBO failImagesBO;

    @Before
    public void setUp() {
        byte[] image = new byte[10];
        failImagesBO = new FailImagesBO(1, image, 1);
    }

    @Test
    public void testEmptyConstructor() {
        FailImagesBO failImagesBO = new FailImagesBO();
        assertEquals(0, failImagesBO.getId());
    }

    @Test
    public void testInitialStateOfFailImages() {
        byte[] image = new byte[10];
        assertEquals(1, failImagesBO.getId());
        assertArrayEquals(image, failImagesBO.getImage());
        assertEquals(1, failImagesBO.getRepairID());

    }

    @Test
    public void testToString() {
        String toStringTester = "FailImagesBO{" +
                "id=" + 1 +
                ", image=" + "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]" +
                ", repairID=" + 1 +
                '}';
        assertEquals(toStringTester, failImagesBO.toString());
    }
}
