package behaviorics.httpRequests;

import behaviorics.HTTPConnection;
import behaviorics.models.State;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HTTPConnection.class, StateRequests.class})
public class StateRequestsTest {

    final State state = new State(1, "texas", "TX");
    final String expectedResponse = "{\"stateID\":" + state.getStateID()
            + ",\"stateAcronym\":\"" + state.getStateAcronym()
            + "\",\"stateName\":\"" + state.getStateName() + "\"}";

    @Test
    public void testGetStateByName() throws Exception {

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(state.getStateID(), StateRequests.getStateByName(state.getStateName()).getStateID());
        Assert.assertEquals(state.getStateName(), StateRequests.getStateByName(state.getStateName()).getStateName());
        Assert.assertEquals(state.getStateAcronym(), StateRequests.getStateByName(state.getStateName()).getStateAcronym());
    }

    @Test
    public void testGetStateByAcronym() throws Exception {

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(state.getStateID(), StateRequests.getStateByAcronym(state.getStateName()).getStateID());
        Assert.assertEquals(state.getStateName(), StateRequests.getStateByAcronym(state.getStateName()).getStateName());
        Assert.assertEquals(state.getStateAcronym(), StateRequests.getStateByAcronym(state.getStateName()).getStateAcronym());
    }

    @Test
    public void testGetAllStates() throws Exception {
        final State state1 = new State(1, "texas", "TX");
        final State state2 = new State(2, "california", "CA");

        List<State> expectedList = new ArrayList<State>() {{
            add(state1);
            add(state2);
        }};

        String expectedResponse = "[{\"stateID\":" + state1.getStateID()
                + ",\"stateAcronym\":\"" + state1.getStateAcronym()
                + "\",\"stateName\":\"" + state1.getStateName() + "\"},"
                + "{\"stateID\":" + state2.getStateID()
                + ",\"stateAcronym\":\"" + state2.getStateAcronym()
                + "\",\"stateName\":\"" + state2.getStateName() + "\"}]";

        PowerMockito.stub(method(HTTPConnection.class, "makeGetRequest")).toReturn(expectedResponse);

        Assert.assertEquals(expectedList.toString(), StateRequests.getAllStates().toString());

    }

}
