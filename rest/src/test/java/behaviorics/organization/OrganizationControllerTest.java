package behaviorics.organization;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OrganizationController.class)
public class OrganizationControllerTest {

    private OrganizationBO organizationBO;
    private String expectedString;

    @InjectMocks
    private OrganizationController organizationController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(organizationController).build();
        organizationBO = new OrganizationBO(1, "University Of Houston");
        expectedString = "{\"id\":1,\"organizationName\":\"University Of Houston\"}";
    }

    @Test
    public void testGetOrganizationByID() throws Exception {
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.when(organizationDAO.getOrganizationById(1)).thenReturn(organizationBO);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(get("/organization/id/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetOrganizationByIDFails() throws Exception {
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.when(organizationDAO.getOrganizationById(2)).thenThrow(new SQLException());
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(get("/organization/id/2")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetOrganizationByOrganizationName() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.when(organizationDAO.getOrganizationByOrganizationName("University Of Houston")).thenReturn(organizationBO);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(get("/organization/University Of Houston")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetOrganizationByOrganizationNameUNAUTHORIZED() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.when(organizationDAO.getOrganizationByOrganizationName("Houston Community College")).thenThrow(new SQLException());
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(get("/organization/Houston Community College")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllOrganizations() throws Exception{
        List<OrganizationBO> organizationList = new ArrayList<>();
        organizationList.add(organizationBO);
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.when((organizationDAO.getAllOrganizations())).thenReturn(organizationList);
        PowerMockito.whenNew(OrganizationDAO.class).withAnyArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(get("/organization")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllOrganizationsUNAUTHORIZED() throws Exception{
        List<OrganizationBO> organizationList = new ArrayList<>();
        organizationList.add(organizationBO);
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.when((organizationDAO.getAllOrganizations())).thenThrow(new SQLException());
        PowerMockito.whenNew(OrganizationDAO.class).withAnyArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(get("/organization")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateOrganizationCREATED() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(post("/organization").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateOrganizationFAILED() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.doThrow(new SQLException()).when(organizationDAO).createOrganization(any(OrganizationBO.class));
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(post("/organization").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateOrganizationUPDATED() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(put("/organization/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrganizationUNAUTHORIZED() throws Exception {
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        Mockito.doThrow(new SQLException()).when(organizationDAO).updateOrganizationById(anyInt(), any(OrganizationBO.class));

        this.mockMvc.perform(put("/organization/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteOrganizationDELETED() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(delete("/organization/1")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteOrganizationUNAUTHORIZED() throws Exception{
        OrganizationDAO organizationDAO = Mockito.mock(OrganizationDAO.class);
        Mockito.doThrow(new SQLException()).when(organizationDAO).deleteOrganizationById(1);
        PowerMockito.whenNew(OrganizationDAO.class).withNoArguments().thenReturn(organizationDAO);
        this.mockMvc.perform(delete("/organization/1")).andExpect(status().isUnauthorized());
    }
}
