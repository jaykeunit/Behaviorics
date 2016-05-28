package behaviorics.entity;

import behaviorics.BehavioricsDatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntityDAO {

    public EntityBO getEntityById(int entityId) throws Exception {

        int id;
        int organizationID;
        String entityName;
        String contactNumber;
        String entityAcronym;

        String query = "SELECT * FROM Entity WHERE entityID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, entityId);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("entityID");
                organizationID = rs.getInt("organizationID");
                entityName = rs.getString("entityName");
                contactNumber = rs.getString("contactNumber");
                entityAcronym = rs.getString("entityAcronym");
            }

        }

        return new EntityBO(id, organizationID, entityName, contactNumber, entityAcronym);
    }

    public EntityBO getEntityByEntityName(String name) throws Exception {

        int id;
        int organizationID;
        String entityName;
        String contactNumber;
        String entityAcronym;

        String query = "SELECT * FROM Entity WHERE entityName = ? ";
        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, name);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("entityID");
                organizationID = rs.getInt("organizationID");
                entityName = rs.getString("entityName");
                contactNumber = rs.getString("contactNumber");
                entityAcronym = rs.getString("entityAcronym");
            }

        }

        return new EntityBO(id, organizationID, entityName, contactNumber, entityAcronym);
    }

    public List<EntityBO> getAllEntitiesByOrganizationID(int organizationID) throws Exception{

        int id;
        String organizationName;
        String entityName;
        String contactNumber;
        String entityAcronym;

        List<EntityBO> entityList = new ArrayList<>();

        String query = "SELECT Entity.entityID, Entity.organizationID, Organization.organizationName, \n" +
                "Entity.entityName, Entity.contactNumber, Entity.entityAcronym\n" +
                "FROM Entity\n" +
                "inner join Organization\n" +
                "\ton  Entity.organizationID = Organization.organizationID\n" +
                "where Organization.organizationID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, organizationID);

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    id = rs.getInt("entityID");
                    organizationID = rs.getInt("organizationID");
                    organizationName = rs.getString("organizationName");
                    entityName = rs.getString("entityName");
                    contactNumber = rs.getString("contactNumber");
                    entityAcronym = rs.getString("entityAcronym");

                    entityList.add(new EntityBO(id, organizationID, organizationName, entityName, contactNumber, entityAcronym));
                }
            }
        }

        return entityList;
    }

    public void createEntity(EntityBO entityBO) throws Exception{

        String query = "INSERT INTO Entity (organizationID, entityName, contactNumber, entityAcronym)  VALUES (?,?,?,?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, entityBO.getOrganizationID());
            pstmt.setString(2, entityBO.getEntityName());
            pstmt.setString(3, entityBO.getContactNumber());
            pstmt.setString(4, entityBO.getEntityAcronym());

            pstmt.executeUpdate();
        }
    }

    public void updateEntityById(int id, EntityBO entityBO) throws Exception{

        String query = "UPDATE Entity SET  organizationID=?, entityName=?,contactNumber=?, entityAcronym=? WHERE entityID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, entityBO.getOrganizationID());
            pstmt.setString(2, entityBO.getEntityName());
            pstmt.setString(3, entityBO.getContactNumber());
            pstmt.setString(4, entityBO.getEntityAcronym());
            pstmt.setInt(5, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteEntityById(int id) throws Exception{

        String query ="DELETE FROM Entity WHERE entityID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
