package behaviorics.organization;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrganizationBOTest {

    private OrganizationBO organizationBo;

    @Before
    public void setUp(){
        organizationBo = new OrganizationBO(1, "University Of Houston");
    }

    @Test
    public void testEmptyConstructor() {
        OrganizationBO organizationBO = new OrganizationBO();
        assertEquals(0, organizationBO.getId());
    }

    @Test
    public void testInitialStateOfOrganization(){
        assertEquals(1, organizationBo.getId());
        assertEquals("University Of Houston", organizationBo.getOrganizationName());
    }

    @Test
    public void testToString(){
        String toStringTester = "id: 1, organizationName: University Of Houston";
        assertEquals(toStringTester, organizationBo.toString());
    }
}
