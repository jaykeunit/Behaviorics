package behaviorics.workingStateImages;

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

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(WorkingStateImagesController.class)
public class WorkingStateImagesControllerTest {

    private WorkingStateImagesBO workingStateImagesBO;
    private String expectedString;

    @InjectMocks
    private WorkingStateImagesController workingStateImagesController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        byte[] image = new byte[10];
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(workingStateImagesController).build();
        workingStateImagesBO = new WorkingStateImagesBO(1, image, 1);
        expectedString = "{\"id\":1,\"image\":\"AAAAAAAAAAAAAA==\",\"cameraID\":1}";
    }

    @Test
    public void testGetWorkingStateImagesByID() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.when(workingStateImagesDAO.getWorkingStateImagesByID(1)).thenReturn(workingStateImagesBO);
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(get("/workingStateImages/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetWorkingStateImagesByIDFails() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.when(workingStateImagesDAO.getWorkingStateImagesByID(4)).thenThrow(new SQLException());
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(get("/workingStateImages/4")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetWorkingStateImagesByCameraID() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.when(workingStateImagesDAO.getWorkingStateImagesByCameraID(1)).thenReturn(workingStateImagesBO);
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(get("/workingStateImages/cameraID/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetWorkingStateImagesByCameraIDFails() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.when(workingStateImagesDAO.getWorkingStateImagesByCameraID(4)).thenThrow(new SQLException());
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(get("/workingStateImages/cameraID/4")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testWorkingStateImagesCREATED() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(post("/workingStateImages").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testWorkingStateImagesCREATEDFails() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.doThrow(new SQLException()).when(workingStateImagesDAO).createWorkingStateImages(any(WorkingStateImagesBO.class));
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(post("/workingStateImages").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateWorkingStateImages() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(put("/workingStateImages/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateWorkingStateImagesFails() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.doThrow(new SQLException()).when(workingStateImagesDAO).updateWorkingStateImagesById(any(Integer.class), any(WorkingStateImagesBO.class));
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(put("/workingStateImages/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteWorkingStateImagesOK() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(delete("/workingStateImages/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteWorkingStateImagesFails() throws Exception {
        WorkingStateImagesDAO workingStateImagesDAO = Mockito.mock(WorkingStateImagesDAO.class);
        Mockito.doThrow(new SQLException()).when(workingStateImagesDAO).deleteWorkingStateImagesById(3);
        PowerMockito.whenNew(WorkingStateImagesDAO.class).withNoArguments().thenReturn(workingStateImagesDAO);

        this.mockMvc.perform(delete("/workingStateImages/3")).andExpect(status().isUnauthorized());
    }
}
