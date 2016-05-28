package behaviorics.states;

import java.util.Arrays;

public class StatesBO {

    private int stateID;
    private String stateName;
    private String stateAcronym;

    public StatesBO() {}

    public StatesBO(int _stateID, String _stateName, String _stateAbbr) {
        stateID = _stateID;
        stateName =_stateName;
        stateAcronym = _stateAbbr;
    }

    public int getStateID() {
        return stateID;
    }

    public String getStateName(){
        return stateName;
    }

    public String getStateAcronym() {
        return stateAcronym;
    }

    @Override
    public String toString() {
        return "stateID: " + stateID +", "
                + "stateAcronym: " + stateAcronym +", "
                + "stateName: " + stateName;
    }
}
