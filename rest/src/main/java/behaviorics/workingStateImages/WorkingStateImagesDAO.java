package behaviorics.workingStateImages;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkingStateImagesDAO {
    public WorkingStateImagesBO getWorkingStateImagesByID(int id) throws Exception {

        int imageID;
        byte[] image;
        int cameraID;

        String query = "SELECT * FROM WorkingStateImages WHERE imageID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();

                imageID = rs.getInt("imageID");
                image = rs.getBytes("image");
                cameraID = rs.getInt("cameraID");
            }
        }

        return new WorkingStateImagesBO(imageID, image, cameraID);
    }

    public WorkingStateImagesBO getWorkingStateImagesByCameraID(int id) throws Exception {

        int imageID;
        byte[] image;
        int cameraID;

        String query = "SELECT * FROM WorkingStateImages WHERE cameraID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();

                imageID = rs.getInt("imageID");
                image = rs.getBytes("image");
                cameraID = rs.getInt("cameraID");
            }
        }

        return new WorkingStateImagesBO(imageID, image, cameraID);
    }

    public void createWorkingStateImages(WorkingStateImagesBO workingStateImagesBO) throws Exception {

        String query = "INSERT INTO WorkingStateImages (image, cameraID)  VALUES (?, ?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setBytes(1, workingStateImagesBO.getImage());
            pstmt.setInt(2, workingStateImagesBO.getCameraID());

            pstmt.executeUpdate();
        }
    }

    public void updateWorkingStateImagesById(int id, WorkingStateImagesBO workingStateImagesBO) throws Exception {

        String query = "UPDATE WorkingStateImages SET image=?, cameraID=? WHERE imageID=?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setBytes(1, workingStateImagesBO.getImage());
            pstmt.setInt(2, workingStateImagesBO.getCameraID());
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteWorkingStateImagesById(int id) throws Exception {
        String query = "DELETE FROM WorkingStateImages WHERE imageID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
