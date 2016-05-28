package behaviorics.floorPlan;

import behaviorics.BehavioricsDatabaseConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FloorPlanDAO {
    public FloorPlanBO getFloorPlanByID(int id) throws Exception {

        int floorPlanID;
        byte[] image;
        int floorID;

        String query = "SELECT * FROM FloorPlan WHERE floorPlanID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                floorPlanID = rs.getInt("floorPlanID");
                image = rs.getBytes("image");
                floorID = rs.getInt("floorID");
            }
        }

        return new FloorPlanBO(floorPlanID, image, floorID);
    }

    public FloorPlanBO getFloorPlanByFloorID(int id) throws Exception {
        int floorPlanID;
        byte[] image;
        int floorID;

        String query = "SELECT * FROM FloorPlan WHERE floorID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();

                floorPlanID = rs.getInt("floorPlanID");
                image = rs.getBytes("image");
                floorID = rs.getInt("floorID");
            }
        }

        return new FloorPlanBO(floorPlanID, image, floorID);
    }

    public void createFloorPlan(FloorPlanBO floorPlanBO) throws Exception {

        String query = "INSERT INTO FloorPlan (image, floorID)  VALUES (?, ?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setBytes(1, floorPlanBO.getImage());
            pstmt.setInt(2, floorPlanBO.getFloorId());

            pstmt.executeUpdate();
        }
    }

    public void updateFloorPlanById(int id, FloorPlanBO floorPlanBO) throws Exception {
        String query = "UPDATE FloorPlan SET image=?, floorID=? WHERE floorPlanID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setBytes(1, floorPlanBO.getImage());
            pstmt.setInt(2, floorPlanBO.getFloorId());
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteFloorPlanById(int id) throws Exception {

        String query = "DELETE FROM FloorPlan WHERE floorPlanID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
