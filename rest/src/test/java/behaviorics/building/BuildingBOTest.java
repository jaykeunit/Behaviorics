package behaviorics.building;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuildingBOTest {

    private BuildingBO buildingBo;

    @Before
    public void setUp() {
        buildingBo = new BuildingBO(1, "Science&Research1", 1, 1234, "University Drive", "Houston", 77004, "SR1", "TX");
    }

    @Test
    public void testEmptyConstructor() {
        BuildingBO buildingBO = new BuildingBO();
        assertEquals(0, buildingBO.getId());
    }

    @Test
    public void testInitialStateOfBuilding() {
        assertEquals(1, buildingBo.getId());
        assertEquals("Science&Research1", buildingBo.getBuildingName());
        assertEquals(1, buildingBo.getEntityID());
        assertEquals(1234, buildingBo.getStreetCode());
        assertEquals("University Drive", buildingBo.getStreetName());
        assertEquals("Houston", buildingBo.getCity());
        assertEquals(77004, buildingBo.getZipcode());
        assertEquals("SR1", buildingBo.getBuildingAcronym());
        assertEquals("TX", buildingBo.getState());
    }

    @Test
    public void testToString() {
        String toStringTester = "id: 1, buildingName: Science&Research1, entityId: 1, streetCode: 1234, streetName: " +
                                "University Drive, city: Houston, zipcode: 77004, buildingAcronym: SR1, state: TX";
        assertEquals(toStringTester, buildingBo.toString());
    }
}