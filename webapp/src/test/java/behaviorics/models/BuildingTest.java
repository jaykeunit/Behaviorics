package behaviorics.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BuildingTest {
    private Building building;

    @Before
    public void setUp() {
        building = new Building(1, "Science&Research1", 1, 1234, "University Drive", "Houston", 77004, "SR1", "TX");
    }

    @Test
    public void testEmptyConstructor() {
        Building buildingBO = new Building();
        Assert.assertEquals(0, buildingBO.getId());
    }

    @Test
    public void testEntityName() {
        building.setEntityName("UH");

        Building buildingTest = new Building(1, "Science&Research1", 1, "UH", 1234, "University Drive", "Houston", 77004, "SR1", "TX");
        Assert.assertEquals(buildingTest.getEntityName(), building.getEntityName());
    }

    @Test
    public void testInitialStateOfBuilding() {
        Assert.assertEquals(1, building.getId());
        Assert.assertEquals("Science&Research1", building.getBuildingName());
        Assert.assertEquals(1, building.getEntityID());
        Assert.assertEquals(1234, building.getStreetCode());
        Assert.assertEquals("University Drive", building.getStreetName());
        Assert.assertEquals("Houston", building.getCity());
        Assert.assertEquals(77004, building.getZipcode());
        Assert.assertEquals("SR1", building.getBuildingAcronym());
        Assert.assertEquals("TX", building.getState());
    }

    @Test
    public void testToString() {
        String toStringTester = "id: 1, buildingName: Science&Research1, entityId: 1, streetCode: 1234, streetName: " +
                "University Drive, city: Houston, zipcode: 77004, buildingAcronym: SR1, state: TX";
        Assert.assertEquals(toStringTester, building.toString());
    }
}