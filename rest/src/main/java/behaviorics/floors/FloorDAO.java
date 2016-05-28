package behaviorics.floors;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloorDAO {

    public FloorBO getFloorById(int _floorID) throws Exception {

        int id;
        int floorNumber;
        int buildingID;
        String floorType;
        String nickname;

        String query = "SELECT * FROM Floors WHERE floorID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, _floorID);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("floorID");
                floorNumber = rs.getInt("floorNumber");
                floorType = rs.getString("floorType");
                buildingID = rs.getInt("buildingID");
                nickname = rs.getString("nickname");
            }
        }
        return new FloorBO(id, buildingID, floorNumber, floorType, nickname);
    }

    public FloorBO getFloor(int _buildingID, int _floorNumber) throws Exception {

        int id;
        int floorNumber;
        int buildingID;
        String floorType;
        String nickname;

        String query = "SELECT * FROM Floors WHERE buildingID=? AND floorNumber=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, _buildingID);
            pstmt.setInt(2, _floorNumber);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("floorID");
                floorNumber = rs.getInt("floorNumber");
                floorType = rs.getString("floorType");
                buildingID = rs.getInt("buildingID");
                nickname = rs.getString("nickname");
            }
        }

        return new FloorBO(id, buildingID, floorNumber, floorType, nickname);
    }

    public List<FloorBO> getFloorsByBuildingID(int _buildingID) throws Exception {
        List<FloorBO> result = new ArrayList<>();

        int id;
        int floorNumber;
        int buildingID;
        String floorType;
        String nickname;

        String query = "SELECT * FROM Floors WHERE buildingID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, _buildingID);

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("floorID");
                    floorNumber = rs.getInt("floorNumber");
                    buildingID = rs.getInt("buildingID");
                    floorType = rs.getString("floorType");
                    nickname = rs.getString("nickname");
                    result.add(new FloorBO(id, buildingID, floorNumber, floorType, nickname));
                }
            }
        }

        return result;
    }

    public List<FloorBO> getAllFloorsForAnOrganization(int _organizationID) throws Exception {
        List<FloorBO> result = new ArrayList<>();

        int id;
        int floorNumber;
        int buildingID;
        String buildingName;
        String floorType;
        String nickname;

        String query = "SELECT Floors.floorID, Floors.floorNumber, Floors.buildingID, Building.buildingName, Floors.floorType, Floors.nickname\n" +
                "FROM Floors\n" +
                "inner join Building\n" +
                "\ton Floors.buildingID = Building.buildingID\n" +
                "inner join Entity\n" +
                "\ton Building.entityID =  Entity.entityID\n" +
                "inner join Organization\n" +
                "\ton  Entity.organizationID = Organization.organizationID\n" +
                "where Organization.organizationID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, _organizationID);

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("floorID");
                    floorNumber = rs.getInt("floorNumber");
                    buildingID = rs.getInt("buildingID");
                    buildingName = rs.getString("buildingName");
                    floorType = rs.getString("floorType");
                    nickname = rs.getString("nickname");
                    result.add(new FloorBO(id, buildingID, buildingName, floorNumber, floorType, nickname));
                }
            }
        }

        return result;
    }

    public void createFloor(FloorBO floorBO) throws Exception {

        String query = "INSERT INTO Floors (floorNumber, buildingID, floorType, nickname) VALUES(?,?,?,?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, floorBO.getFloorNumber());
            pstmt.setInt(2, floorBO.getBuildingID());
            pstmt.setString(3, floorBO.getFloorType());
            pstmt.setString(4, floorBO.getNickname());
            pstmt.executeUpdate();
        }

    }

    // Reform UPDATE query
    public void updateFloorById(int id, FloorBO floorBO) throws Exception {

        String query = "UPDATE Floors SET floorNumber=?,floorType=?,nickname=? WHERE floorID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, floorBO.getFloorNumber());
            pstmt.setString(2, floorBO.getFloorType());
            pstmt.setString(3, floorBO.getFloorType());
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
        }

    }

    public void deleteFloorById(int id) throws Exception {

        String query = "DELETE FROM Floors WHERE floorId = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
