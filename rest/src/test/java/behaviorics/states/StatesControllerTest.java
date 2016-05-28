package behaviorics.states;

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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StatesController.class)
public class StatesControllerTest {


    private StatesBO statesBO;
    private List<StatesBO> allStatesList;

    private String expectedString;
    private String expectedAllString;
    @InjectMocks
    private StatesController statesController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(statesController).build();
        statesBO = new StatesBO(5,"Texas","TX");
        allStatesList = Arrays.asList(statesBO,new StatesBO(6,"Alabama","AL"),new StatesBO(7,"Florida","FL"));
        expectedString = "{\"stateID\":5,\"stateName\":\"Texas\",\"stateAcronym\":\"TX\"}";

        expectedAllString = "[{\"stateID\":5,\"stateName\":\"Texas\",\"stateAcronym\":\"TX\"}" + ","
                            + "{\"stateID\":6,\"stateName\":\"Alabama\",\"stateAcronym\":\"AL\"}" + ","
                            + "{\"stateID\":7,\"stateName\":\"Florida\",\"stateAcronym\":\"FL\"}]";
    }

    @Test
    public void testGetStateByStateName() throws Exception {
        StatesDAO statesDAO = PowerMockito.mock(StatesDAO.class);
        PowerMockito.when(statesDAO.getStateByStateName("Texas")).thenReturn(statesBO);

        PowerMockito.whenNew(StatesDAO.class).withNoArguments().thenReturn(statesDAO);

        this.mockMvc.perform(get("/states/Texas")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetStateByStateNameFails() throws Exception {
        StatesDAO statesDAO = PowerMockito.mock(StatesDAO.class);
        PowerMockito.when(statesDAO.getStateByStateName("Texas")).thenThrow(new SQLException());

        PowerMockito.whenNew(StatesDAO.class).withNoArguments().thenReturn(statesDAO);

        this.mockMvc.perform(get("/states/Texas")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetStateByStateAcronym() throws Exception {
        StatesDAO statesDAO = PowerMockito.mock(StatesDAO.class);
        PowerMockito.when(statesDAO.getStateByStateAcronym("TX")).thenReturn(statesBO);

        PowerMockito.whenNew(StatesDAO.class).withNoArguments().thenReturn(statesDAO);

        this.mockMvc.perform(get("/states/acr/TX")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetStateByStateAcronymFails() throws Exception {
        StatesDAO statesDAO = PowerMockito.mock(StatesDAO.class);
        PowerMockito.when(statesDAO.getStateByStateAcronym("TX")).thenThrow(new SQLException());

        PowerMockito.whenNew(StatesDAO.class).withNoArguments().thenReturn(statesDAO);

        this.mockMvc.perform(get("/states/acr/TX")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllStates() throws Exception {
        StatesDAO statesDAO = PowerMockito.mock(StatesDAO.class);
        PowerMockito.when(statesDAO.getAllStates()).thenReturn(allStatesList);

        PowerMockito.whenNew(StatesDAO.class).withNoArguments().thenReturn(statesDAO);

        this.mockMvc.perform(get("/states")).andExpect(status().isOk()).andExpect(content().string(expectedAllString));
    }

    @Test
    public void testGetAllStatesFails() throws Exception {
        StatesDAO statesDAO = PowerMockito.mock(StatesDAO.class);
        PowerMockito.when(statesDAO.getAllStates()).thenThrow(new SQLException());

        PowerMockito.whenNew(StatesDAO.class).withNoArguments().thenReturn(statesDAO);

        this.mockMvc.perform(get("/states")).andExpect(status().isUnauthorized());
    }

}