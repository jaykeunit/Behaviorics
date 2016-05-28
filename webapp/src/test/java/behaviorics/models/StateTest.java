package behaviorics.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    private State states;
    @Before
    public void setUp() {
        states = new State(5,"Texas","TX");
    }

    @Test
    public void testEmptyConstructor(){
        State state = new State();
        assertEquals(0,state.getStateID());
    }

    @Test
    public void testGetStateID() {

        assertEquals(5, states.getStateID());
    }

    @Test
    public void testGetStateName(){
        assertEquals("Texas", states.getStateName());
    }

    @Test
    public void testGetStateAcronym() {
        assertEquals("TX", states.getStateAcronym());
    }

    @Test
    public void testToString() {
        assertEquals("stateID: 5, stateAcronym: TX, stateName: Texas", states.toString());
    }
}