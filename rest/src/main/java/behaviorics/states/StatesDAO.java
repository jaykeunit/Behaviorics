package behaviorics.states;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatesDAO {

    public StatesBO getStateByStateName(String name)throws Exception{
        int stateID;
        String stateName;
        String stateAbbr;

        String query = "SELECT * FROM States WHERE stateName = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, name);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                stateID = rs.getInt("stateID");
                stateName = rs.getString("stateName");
                stateAbbr = rs.getString("stateAbbr");
            }
        }
        return new StatesBO(stateID,stateName,stateAbbr);
    }

    public StatesBO getStateByStateAcronym(String abbr)throws Exception{
        int stateID;
        String stateName;
        String stateAbbr;

        String query = "SELECT * FROM States WHERE stateAbbr = ?";
        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, abbr);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                stateID = rs.getInt("stateID");
                stateName = rs.getString("stateName");
                stateAbbr = rs.getString("stateAbbr");
            }
        }
        return new StatesBO(stateID,stateName,stateAbbr);
    }


    public List<StatesBO> getAllStates()throws Exception{

        List<StatesBO> allstates = new ArrayList<>();

        String query = "SELECT * FROM States;";
        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                while (rs.next()) {

                    int stateID = rs.getInt("stateID");
                    String stateName = rs.getString("stateName");
                    String stateAbbr = rs.getString("stateAbbr");

                    allstates.add(new StatesBO(stateID, stateName, stateAbbr));
                }
            }
        }
        return allstates;
    }
}
