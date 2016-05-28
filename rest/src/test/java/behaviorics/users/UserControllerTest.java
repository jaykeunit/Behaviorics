package behaviorics.users;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserController.class)
public class UserControllerTest {

    private UserBO userBO;
    private String expectedString;
    byte[] salt = new byte[10];
    byte[] hash = new byte[10];

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userBO = new UserBO(1, "Tusername", salt, hash, "admin", 1);
        expectedString = "{\"id\":1,\"userName\":\"Tusername\",\"salt\":\"AAAAAAAAAAAAAA==\",\"hash\":\"AAAAAAAAAAAAAA==\",\"privilege\":\"admin\",\"organizationID\":1}";
    }

    @Test
    public void testUserByUserName() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.when(userDAO.getUserByUserName("Tusername")).thenReturn(userBO);

        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(get("/users/Tusername").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUserByUserNameFails() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.when(userDAO.getUserByUserName("Tusername")).thenThrow(new SQLException());

        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(get("/users/Tusername")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllUsersByOrganizationID() throws Exception {
        List<UserBO> userBOList = new ArrayList<>();
        userBOList.add(userBO);

        UserDAO userDAO = Mockito.mock(UserDAO.class);
        Mockito.when((userDAO.getAllUserByOrganizationID(100))).thenReturn(userBOList);
        PowerMockito.whenNew(UserDAO.class).withAnyArguments().thenReturn(userDAO);

        this.mockMvc.perform(get("/users/organizationID/100")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString.substring(0, expectedString.length()-1) + ",\"organizationName\":null}]"));
    }

    @Test
    public void testGetAllUsersByOrganizationIDFails() throws Exception {
        List<UserBO> userBOList = new ArrayList<>();
        userBOList.add(userBO);

        UserDAO userDAO = Mockito.mock(UserDAO.class);
        Mockito.when((userDAO.getAllUserByOrganizationID(1))).thenThrow(new SQLException());
        PowerMockito.whenNew(UserDAO.class).withAnyArguments().thenReturn(userDAO);

        this.mockMvc.perform(get("/users/organizationID/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateUserCREATED() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateUsersUNAUTHORIZED() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        Mockito.doThrow(new SQLException()).when(userDAO).createUser(any(UserBO.class));
        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateUsersSuccessfulPUT() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(put("/users/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateUsersFails() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        Mockito.doThrow(new SQLException()).when(userDAO).updateUserById(any(Integer.class), any(UserBO.class));
        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(put("/users/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteBuildingOK() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(delete("/users/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteBuildingUNAUTHORIZED() throws Exception {
        UserDAO userDAO = PowerMockito.mock(UserDAO.class);
        Mockito.doThrow(new SQLException()).when(userDAO).deleteUserById(3);
        PowerMockito.whenNew(UserDAO.class).withNoArguments().thenReturn(userDAO);

        this.mockMvc.perform(delete("/users/3")).andExpect(status().isUnauthorized());
    }

}