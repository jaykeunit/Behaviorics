package behaviorics.building;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildingDAO {

    public BuildingBO getBuildingById(int queryID) throws Exception {

        int id;
        String buildingName;
        int entityID;
        int streetCode;
        String streetName;
        String city;
        int zipcode;
        String buildingAcronym;
        String state;

        String query = "SELECT * FROM Building WHERE buildingID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, queryID);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("buildingID");
                buildingName = rs.getString("buildingName");
                entityID = rs.getInt("entityID");
                streetCode = rs.getInt("streetCode");
                streetName = rs.getString("streetName");
                city = rs.getString("city");
                zipcode = rs.getInt("zipcode");
                buildingAcronym = rs.getString("buildingAcronym");
                state = rs.getString("state");
            }

        }

        return new BuildingBO(id, buildingName, entityID, streetCode, streetName, city, zipcode, buildingAcronym, state);
    }

    public BuildingBO getBuildingByBuildingName(String name) throws Exception {

        int id;
        String buildingName;
        int entityID;
        int streetCode;
        String streetName;
        String city;
        int zipcode;
        String buildingAcronym;
        String state;

        String query = "SELECT * FROM Building WHERE buildingName = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, name);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("buildingID");
                buildingName = rs.getString("buildingName");
                entityID = rs.getInt("entityID");
                streetCode = rs.getInt("streetCode");
                streetName = rs.getString("streetName");
                city = rs.getString("city");
                zipcode = rs.getInt("zipcode");
                buildingAcronym = rs.getString("buildingAcronym");
                state = rs.getString("state");
            }

        }

        return new BuildingBO(id, buildingName, entityID, streetCode, streetName, city, zipcode, buildingAcronym, state);
    }

    public List<BuildingBO> getAllBuildingsByEntityID(int entityID) throws Exception {

        int id;
        String buildingName;
        int streetCode;
        String streetName;
        String city;
        int zipcode;
        String buildingAcronym;
        String state;

        List<BuildingBO> buildingList = new ArrayList<>();

        String query = "SELECT * FROM Building WHERE entityID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, entityID);

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("buildingID");
                    buildingName = rs.getString("buildingName");
                    entityID = rs.getInt("entityID");
                    streetCode = rs.getInt("streetCode");
                    streetName = rs.getString("streetName");
                    city = rs.getString("city");
                    zipcode = rs.getInt("zipcode");
                    buildingAcronym = rs.getString("buildingAcronym");
                    state = rs.getString("state");

                    buildingList.add(new BuildingBO(id, buildingName, entityID, streetCode, streetName, city, zipcode, buildingAcronym, state));
                }
            }
        }

        return buildingList;
    }

    public List<BuildingBO> getAllBuildingsByOrganizationID(int organizationID) throws Exception {

        int id;
        String buildingName;
        int entityID;
        String entityName;
        int streetCode;
        String streetName;
        String city;
        int zipcode;
        String buildingAcronym;
        String state;

        List<BuildingBO> buildingList = new ArrayList<>();

        String query = "SELECT Building.buildingID, Building.buildingName, Building.entityID, Entity.entityName, Building.streetCode, Building.streetName, \n" +
                "Building.city, Building.zipcode, Building.buildingAcronym, Building.state\n" +
                "FROM Building\n" +
                "inner join Entity\n" +
                "\ton Building.entityID = Entity.entityID\n" +
                "inner join Organization\n" +
                "\ton  Entity.organizationID = Organization.organizationID\n" +
                "where Organization.organizationID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, organizationID);

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("buildingID");
                    buildingName = rs.getString("buildingName");
                    entityID = rs.getInt("entityID");
                    entityName = rs.getString("entityName");
                    streetCode = rs.getInt("streetCode");
                    streetName = rs.getString("streetName");
                    city = rs.getString("city");
                    zipcode = rs.getInt("zipcode");
                    buildingAcronym = rs.getString("buildingAcronym");
                    state = rs.getString("state");

                    buildingList.add(new BuildingBO(id, buildingName, entityID, entityName, streetCode, streetName, city, zipcode, buildingAcronym, state));
                }
            }
        }

        return buildingList;
    }

    public void createBuilding(BuildingBO buildingBO) throws Exception {

        String query = "INSERT INTO Building (buildingName, entityID, streetCode, streetName, city, zipcode, buildingAcronym, state)  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, buildingBO.getBuildingName());
            pstmt.setInt(2, buildingBO.getEntityID());
            pstmt.setInt(3, buildingBO.getStreetCode());
            pstmt.setString(4, buildingBO.getStreetName());
            pstmt.setString(5, buildingBO.getCity());
            pstmt.setInt(6, buildingBO.getZipcode());
            pstmt.setString(7, buildingBO.getBuildingAcronym());
            pstmt.setString(8, buildingBO.getState());

            pstmt.executeUpdate();
        }
    }

    public void updateBuildingById(int id, BuildingBO buildingBO) throws Exception {

        String query = "UPDATE Building SET buildingName=?, entityID=?, streetCode=?, streetName=?, city=?, zipcode=?, buildingAcronym=?, state=? WHERE buildingID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, buildingBO.getBuildingName());
            pstmt.setInt(2, buildingBO.getEntityID());
            pstmt.setInt(3, buildingBO.getStreetCode());
            pstmt.setString(4, buildingBO.getStreetName());
            pstmt.setString(5, buildingBO.getCity());
            pstmt.setInt(6, buildingBO.getZipcode());
            pstmt.setString(7, buildingBO.getBuildingAcronym());
            pstmt.setString(8, buildingBO.getState());
            pstmt.setInt(9, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteBuildingById(int id) throws Exception {

        String query = "DELETE FROM Building WHERE buildingID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
        pstmt.setInt(1,id);

        pstmt.executeUpdate();
        }
    }
}
