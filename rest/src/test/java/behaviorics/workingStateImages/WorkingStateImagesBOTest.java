package behaviorics.workingStateImages;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class WorkingStateImagesBOTest {

    private WorkingStateImagesBO workingStateImagesBO;

    @Before
    public void setUp() {
        byte[] image = new byte[10];
        workingStateImagesBO = new WorkingStateImagesBO(1, image, 1);
    }

    @Test
    public void testEmptyConstructor() {
        WorkingStateImagesBO workingStateImagesBO = new WorkingStateImagesBO();
        assertEquals(0, workingStateImagesBO.getId());
    }

    @Test
    public void testInitialStateOfWorkingStateImages() {
        byte[] image = new byte[10];
        assertEquals(1, workingStateImagesBO.getId());
        assertArrayEquals(image, workingStateImagesBO.getImage());
        assertEquals(1, workingStateImagesBO.getCameraID());

    }

    @Test
    public void testToString() {
        String toStringTester = "WorkingStateImagesBO{" +
                "id=" + 1 +
                ", image=" + "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]" +
                ", cameraID=" + 1 +
                '}';
        assertEquals(toStringTester, workingStateImagesBO.toString());
    }
}
