package behaviorics.states;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatesBOTest {

    private StatesBO statesBO;
    @Before
    public void setUp() {
        statesBO = new StatesBO(5,"Texas","TX");
    }

    @Test
    public void testEmptyConstructor(){
        StatesBO state = new StatesBO();
        assertEquals(0,state.getStateID());
    }

    @Test
    public void testGetStateID() {

        assertEquals(5,statesBO.getStateID());
    }

    @Test
    public void testGetStateName(){
        assertEquals("Texas",statesBO.getStateName());
    }

    @Test
    public void testGetStateAcronym() {
        assertEquals("TX",statesBO.getStateAcronym());
    }

    @Test
    public void testToString() {
        assertEquals("stateID: 5, stateAcronym: TX, stateName: Texas",statesBO.toString());
    }
}