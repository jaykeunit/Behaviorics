package behaviorics.entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EntityBOTest {

    private EntityBO entityBo;

    @Before
    public void setUp(){
        entityBo = new EntityBO(1, 1, "UH Main", "7137432255","UH");
    }

    @Test
    public void testEmptyConstructor() {
        EntityBO entityBO = new EntityBO();
        assertEquals(0, entityBO.getID());
    }

    @Test
    public void testInitialStateEntity(){
        assertEquals(1, entityBo.getID());
        assertEquals(1, entityBo.getOrganizationID());
        assertEquals("UH Main", entityBo.getEntityName());
        assertEquals("7137432255", entityBo.getContactNumber());
        assertEquals("UH", entityBo.getEntityAcronym());
    }

    @Test
    public void testToString(){
        String toStringTester = "id: 1, organizationID: 1, entityName: UH Main, contactNumber: " +
        "7137432255, entityAcronym: UH";
        assertEquals(toStringTester, entityBo.toString());
    }
}
