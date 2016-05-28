package behaviorics.cameras;

import behaviorics.floors.FloorBO;
import behaviorics.floors.FloorDAO;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CamerasController.class)
public class CamerasControllerTest {
    private CamerasBO camerasBO;
    private String expectedString;

    @InjectMocks
    private CamerasController camerasController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(camerasController).build();
        camerasBO = new CamerasBO(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        expectedString = "{\"id\":1,\"cameraName\":\"testCamera\",\"feedIP\":\"testIP\",\"feedCredential\":\"testFeedCred\",\"feedPassword\":\"testPassword\",\"locationX\":0.0,\"locationY\":0.0,\"dateInstalled\":\"3916-04-27\",\"floorID\":1,\"vendorName\":\"vendorName\",\"localMaintenanceName\":\"maintenanceName\",\"localMaintenanceNumber\":\"maintenanceNum\",\"vendorPhoneNumber\":\"vendorNum\",\"vendorEmail\":\"vendorEmail\",\"warrantyExpiration\":\"warrantyExpiration\",\"cameraStatus\":\"Working\",\"httpPort\":88,\"onvifPort\":888,\"orgId\":1}";
    }

    @Test
    public void testGetCameraByCameraName() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when(camerasDAO.getCameraByCameraName("testCamera")).thenReturn(camerasBO);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(get("/cameras/testCamera")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetCameraByCameraNameFails() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when(camerasDAO.getCameraByCameraName("Library")).thenThrow(new SQLException());
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(get("/cameras/Library")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllCameras() throws Exception{
        List<CamerasBO> camerasBOList = new ArrayList<>();
        camerasBOList.add(camerasBO);
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when((camerasDAO.getAllCameras())).thenReturn(camerasBOList);
        PowerMockito.whenNew(CamerasDAO.class).withAnyArguments().thenReturn(camerasDAO);
        this.mockMvc.perform(get("/cameras/all")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllCamerasUNAUTHORIZED() throws Exception{
        List<CamerasBO> camerasBOList = new ArrayList<>();
        camerasBOList.add(camerasBO);
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when((camerasDAO.getAllCameras())).thenThrow(new SQLException());
        PowerMockito.whenNew(CamerasDAO.class).withAnyArguments().thenReturn(camerasDAO);
        this.mockMvc.perform(get("/cameras/all")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllCamerasByOrgId() throws Exception{
        List<CamerasBO> camerasBOList = new ArrayList<>();
        camerasBOList.add(camerasBO);
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when((camerasDAO.getAllCamerasByOrgId(1))).thenReturn(camerasBOList);
        PowerMockito.whenNew(CamerasDAO.class).withAnyArguments().thenReturn(camerasDAO);
        this.mockMvc.perform(get("/cameras/orgID/1")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllCamerasByOrgIdUNAUTHORIZED() throws Exception{
        List<CamerasBO> camerasBOList = new ArrayList<>();
        camerasBOList.add(camerasBO);
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when((camerasDAO.getAllCamerasByOrgId(1))).thenThrow(new SQLException());
        PowerMockito.whenNew(CamerasDAO.class).withAnyArguments().thenReturn(camerasDAO);
        this.mockMvc.perform(get("/cameras/orgID/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllCamerasByFloorID() throws Exception {
        List<CamerasBO> camerasList = new ArrayList<>();
        camerasList.add(camerasBO);

        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when((camerasDAO.getAllCamerasByFloorID(1))).thenReturn(camerasList);
        PowerMockito.whenNew(CamerasDAO.class).withAnyArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(get("/cameras/floorID/1")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllCamerasByFloorFails() throws Exception {
        List<CamerasBO> camerasList = new ArrayList<>();
        camerasList.add(camerasBO);

        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.when((camerasDAO.getAllCamerasByFloorID(10))).thenThrow(new SQLException());
        PowerMockito.whenNew(CamerasDAO.class).withAnyArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(get("/cameras/floorID/10")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateCameraCREATED() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        FloorBO floorBO = Mockito.mock(FloorBO.class);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getFloorById(Matchers.anyInt())).thenReturn(floorBO);
        Mockito.when(floorBO.getNickname()).thenReturn("Acronym");
        Mockito.when(camerasDAO.getCameraCountByFloor(Matchers.anyInt())).thenReturn(1);

        this.mockMvc.perform(post("/cameras").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateCameraFails() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.doThrow(new SQLException()).when(camerasDAO).createCamera(any(CamerasBO.class));
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(post("/cameras").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateCameraSuccessfulPUT() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(put("/cameras/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateCameraFails() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.doThrow(new SQLException()).when(camerasDAO).updateCameraById(any(Integer.class), any(CamerasBO.class));
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(put("/cameras/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteCameraOK() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(delete("/cameras/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteCameraFails() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        Mockito.doThrow(new SQLException()).when(camerasDAO).deleteCameraById(3);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);

        this.mockMvc.perform(delete("/cameras/3")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetCameraByIdOK() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);
        Mockito.when(camerasDAO.getCameraByCameraId(Matchers.anyInt())).thenReturn(camerasBO);

        this.mockMvc.perform(get("/camera/3")).andExpect(status().isOk());
    }

    @Test
    public void testGetCameraByIdFails() throws Exception {
        CamerasDAO camerasDAO = Mockito.mock(CamerasDAO.class);
        PowerMockito.whenNew(CamerasDAO.class).withNoArguments().thenReturn(camerasDAO);
        Mockito.doThrow(new SQLException()).when(camerasDAO).getCameraByCameraId(Matchers.anyInt());

        this.mockMvc.perform(get("/camera/3")).andExpect(status().isInternalServerError());
    }
}
