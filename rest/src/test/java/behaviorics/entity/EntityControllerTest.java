package behaviorics.entity;

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
@PrepareForTest(EntityController.class)
public class EntityControllerTest {

    private EntityBO entityBO;
    private String expectedString;

    @InjectMocks
    private EntityController entityController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(entityController).build();
        entityBO = new EntityBO(2, 1, "UH Victoria", "8779704848", "UHV");
        expectedString = "{\"id\":2,\"organizationID\":1,\"organizationName\":null,\"entityName\":\"UH Victoria\",\"contactNumber\":\"8779704848\",\"entityAcronym\":\"UHV\"}";
    }

    @Test
    public void testGetEntityByID() throws Exception {
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.when(entityDAO.getEntityById(2)).thenReturn(entityBO);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(get("/entity/id/2")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetEntityByIDFails() throws Exception {
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.when(entityDAO.getEntityById(2)).thenThrow(new SQLException());
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(get("/entity/id/2")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetEntityByEntityName() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.when(entityDAO.getEntityByEntityName("UH Victoria")).thenReturn(entityBO);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(get("/entity/UH Victoria")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetEntityByEntityNameUNAUTHORIZED() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.when(entityDAO.getEntityByEntityName("UH Main")).thenThrow(new SQLException());
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(get("/entity/UH Main")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllEntitiesByOrganizationID() throws Exception{
        List<EntityBO> entityList = new ArrayList<>();
        entityList.add(entityBO);
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.when((entityDAO.getAllEntitiesByOrganizationID(1))).thenReturn(entityList);
        PowerMockito.whenNew(EntityDAO.class).withAnyArguments().thenReturn(entityDAO);
        this.mockMvc.perform(get("/entity/organizationID/1")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllEntitiesByOrganizationIDUNAUTHORIZED() throws Exception{
        List<EntityBO> entityList = new ArrayList<>();
        entityList.add(entityBO);
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.when((entityDAO.getAllEntitiesByOrganizationID(1))).thenThrow(new SQLException());
        PowerMockito.whenNew(EntityDAO.class).withAnyArguments().thenReturn(entityDAO);
        this.mockMvc.perform(get("/entity/organizationID/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateEntityCREATED() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(post("/entity").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateEntityFAILED() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.doThrow(new SQLException()).when(entityDAO).createEntity(any(EntityBO.class));
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(post("/entity").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isInternalServerError());
    }

    @Test
    public void testUpdateEntityUPDATED() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(put("/entity/2").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateEntityUNAUTHORIZED() throws Exception {
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        Mockito.doThrow(new SQLException()).when(entityDAO).updateEntityById(anyInt(), any(EntityBO.class));

        this.mockMvc.perform(put("/entity/2").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteEntityDELETED() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(delete("/entity/2")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteEntityUNAUTHORIZED() throws Exception{
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.doThrow(new SQLException()).when(entityDAO).deleteEntityById(2);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        this.mockMvc.perform(delete("/entity/2")).andExpect(status().isUnauthorized());
    }
}
