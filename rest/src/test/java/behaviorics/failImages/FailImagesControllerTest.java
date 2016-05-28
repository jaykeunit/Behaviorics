package behaviorics.failImages;

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
@PrepareForTest(FailImagesController.class)
public class FailImagesControllerTest {

    private FailImagesBO failImagesBO;
    private String expectedString;

    @InjectMocks
    private FailImagesController failImagesController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        byte[] image = new byte[10];
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(failImagesController).build();
        failImagesBO = new FailImagesBO(1, image, 1);
        expectedString = "{\"id\":1,\"image\":\"AAAAAAAAAAAAAA==\",\"repairID\":1}";
    }

    @Test
    public void testGetFailImagesByID() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.when(failImagesDAO.getFailImagesByID(1)).thenReturn(failImagesBO);
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(get("/failImages/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetFailImagesByIDFails() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.when(failImagesDAO.getFailImagesByID(4)).thenThrow(new SQLException());
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(get("/failImages/4")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetFailImagesByRepairID() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.when(failImagesDAO.getFailImagesByRepairID(1)).thenReturn(failImagesBO);
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(get("/failImages/repairID/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetFailImagesByRepairIDIDFails() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.when(failImagesDAO.getFailImagesByRepairID(4)).thenThrow(new SQLException());
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(get("/failImages/repairID/4")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testFailImagesCREATED() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(post("/failImages").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testFailImagesCREATEDFails() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.doThrow(new SQLException()).when(failImagesDAO).createFailImages(any(FailImagesBO.class));
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(post("/failImages").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateFailImages() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(put("/failImages/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateFailImagesFails() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.doThrow(new SQLException()).when(failImagesDAO).updateFailImagesById(any(Integer.class), any(FailImagesBO.class));
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(put("/failImages/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteFailImagesOK() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(delete("/failImages/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteFailImagesFails() throws Exception {
        FailImagesDAO failImagesDAO = Mockito.mock(FailImagesDAO.class);
        Mockito.doThrow(new SQLException()).when(failImagesDAO).deleteFailImagesById(3);
        PowerMockito.whenNew(FailImagesDAO.class).withNoArguments().thenReturn(failImagesDAO);

        this.mockMvc.perform(delete("/failImages/3")).andExpect(status().isUnauthorized());
    }
}
