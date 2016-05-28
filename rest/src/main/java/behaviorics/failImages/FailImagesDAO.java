package behaviorics.failImages;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FailImagesDAO {

    public FailImagesBO getFailImagesByID(int id) throws Exception {

        int failImagesID;
        byte[] image;
        int repairID;

        String query = "SELECT * FROM FailImages WHERE failImagesID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();

                failImagesID = rs.getInt("failImagesID");
                image = rs.getBytes("image");
                repairID = rs.getInt("repairID");
            }

        }

        return new FailImagesBO(failImagesID, image, repairID);
    }

    public FailImagesBO getFailImagesByRepairID(int id) throws Exception {

        int failImagesID;
        byte[] image;
        int repairID;

        String query = "SELECT * FROM FailImages WHERE repairID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();

                failImagesID = rs.getInt("failImagesID");
                image = rs.getBytes("image");
                repairID = rs.getInt("repairID");
            }

        }
        return new FailImagesBO(failImagesID, image, repairID);
    }

    public void createFailImages(FailImagesBO failImagesBO) throws Exception {
        String query = "INSERT INTO FailImages (image, repairID)  VALUES (?, ?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setBytes(1, failImagesBO.getImage());
            pstmt.setInt(2, failImagesBO.getRepairID());

            pstmt.executeUpdate();
        }
    }

    public void updateFailImagesById(int id, FailImagesBO failImagesBO) throws Exception {

        String query = "UPDATE FailImages SET image=?, repairID=? WHERE failImagesID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setBytes(1, failImagesBO.getImage());
            pstmt.setInt(2, failImagesBO.getRepairID());
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteFailImagesById(int id) throws Exception {

        String query = "DELETE FROM FailImages WHERE failImagesID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
