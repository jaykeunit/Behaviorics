package behaviorics.controllers;

import behaviorics.ManagementService;
import behaviorics.models.Entity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ManagementService.class, ManagementController.class})
public class ManagementControllerTest {
    @InjectMocks
    private ManagementController managementController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(managementController).build();
    }

    @Test
    public void testReportsGET() throws Exception {
        Entity entity = new Entity(1, 1, "TESTER", "7137432255","TTT");

        managementController.service = Mockito.mock(ManagementService.class);
        Mockito.when(managementController.service.getEntities(Matchers.anyInt())).thenReturn(Arrays.asList(entity));

        this.mockMvc.perform(get("/manage")).andExpect(status().isOk()).andExpect(view().name("managementPage"));
    }

    @Test
    public void testReportsGETFAILS() throws Exception {
        managementController.service = Mockito.mock(ManagementService.class);
        Mockito.when(managementController.service.getOrganizationToFloorsList()).thenThrow(new SQLException());

        this.mockMvc.perform(get("/manage")).andExpect(status().isOk()).andExpect(view().name("errorPage"));
    }

    @Test
    public void testFloorPlanGET() throws Exception {
        byte[] bytes = "Expected Message".getBytes();

        managementController.service = Mockito.mock(ManagementService.class);
        Mockito.when(managementController.service.getImageAsByteArray(1)).thenReturn(bytes);

        this.mockMvc.perform(get("/floorPlan/1")).andExpect(status().isOk()).andExpect(content().string("Expected Message"));
    }

    @Test
    public void testFloorPlanFAILS() throws Exception {
        managementController.service = Mockito.mock(ManagementService.class);
        Mockito.when(managementController.service.getImageAsByteArray(1)).thenThrow(new SQLException());

        this.mockMvc.perform(get("/floorPlan/1")).andExpect(status().isNotFound());
    }
}