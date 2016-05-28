package behaviorics.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityTest {
    private Entity entity;

    @Before
    public void setUp(){
        entity = new Entity(1, 1, "UH Main", "7137432255","SR1");
    }

    @Test
    public void testEmptyConstructor() {
        Entity entityBO = new Entity();
        assertEquals(0, entityBO.getID());
    }

    @Test
    public void testGetID(){
        assertEquals(1, entity.getID());
    }

    @Test
    public void testGetOrganizationID(){
        assertEquals(1, entity.getOrganizationID());
    }

    @Test
    public void testGetEntityName(){
        assertEquals("UH Main", entity.getEntityName());
    }

    @Test
    public void testGetContactNumber(){
        assertEquals("7137432255", entity.getContactNumber());
    }

    @Test
    public void testGetEntityAcronym(){
        assertEquals("SR1", entity.getEntityAcronym());
    }

    @Test
    public void testToString(){
        String toStringTester = "id: 1, organizationID: 1, entityName: UH Main, contactNumber: " +
                "7137432255, entityAcronym: SR1";
        assertEquals(toStringTester, entity.toString());
    }

}