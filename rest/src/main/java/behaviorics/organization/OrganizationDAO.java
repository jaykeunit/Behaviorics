package behaviorics.organization;

import behaviorics.BehavioricsDatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO {

    public OrganizationBO getOrganizationById(int queryID) throws Exception {

        int id;
        String organizationName;

        String query = "SELECT * FROM Organization WHERE organizationID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, queryID);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("organizationID");
                organizationName = rs.getString("organizationName");
            }

        }

        return new OrganizationBO(id, organizationName);
    }

    public OrganizationBO getOrganizationByOrganizationName(String name) throws Exception {

        int id;
        String organizationName;

        String query = "SELECT * FROM Organization WHERE organizationName = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, name);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("organizationID");
                organizationName = rs.getString("organizationName");
            }

        }

        return new OrganizationBO(id, organizationName);
    }

    public List<OrganizationBO> getAllOrganizations() throws Exception {

        int id;
        String organizationName;

        List<OrganizationBO> organizationList = new ArrayList<>();

        String query = "SELECT * FROM Organization";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("organizationID");
                    organizationName = rs.getString("organizationName");

                    organizationList.add(new OrganizationBO(id, organizationName));
                }
            }
        }

        return organizationList;
    }

    public void createOrganization(OrganizationBO organizationBO) throws Exception {

        String query = "INSERT INTO Organization (organizationName)  VALUES (?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, organizationBO.getOrganizationName());

            pstmt.executeUpdate();
        }
    }

    public void updateOrganizationById(int id, OrganizationBO organizationBO) throws Exception {

        String query = "UPDATE Organization SET organizationName=? WHERE organizationID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, organizationBO.getOrganizationName());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteOrganizationById(int id) throws Exception {

        String query = "DELETE FROM Organization WHERE organizationID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1,id);

            pstmt.executeUpdate();
        }
    }
}
