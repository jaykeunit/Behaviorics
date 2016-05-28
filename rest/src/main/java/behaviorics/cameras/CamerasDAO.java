package behaviorics.cameras;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CamerasDAO {

    public CamerasBO getCameraByCameraName(String name) throws Exception {

        int id;
        String cameraName;
        String feedIP;
        String feedCredential;
        String feedPassword ;
        double locationX;
        double locationY;
        Date dateInstalled;
        int floorID;
        String vendorName ;
        String localMaintenanceName ;
        String localMaintenanceNumber;
        String vendorPhoneNumber;
        String vendorEmail;
        String warrantyExpiration;
        String cameraStatus;
        int httpPort;
        int onvifPort;
        int orgId;

        String query = "SELECT * FROM Cameras WHERE cameraName = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, name);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("cameraID");
                cameraName = rs.getString("cameraName");
                feedIP = rs.getString("feedIP");
                feedCredential = rs.getString("feedCredential");
                feedPassword = rs.getString("feedPassword");
                locationX = rs.getDouble("locationX");
                locationY = rs.getDouble("locationY");
                dateInstalled = rs.getDate("dateInstalled");
                floorID = rs.getInt("floorID");
                vendorName = rs.getString("vendorName");
                localMaintenanceName = rs.getString("localMaintenanceName");
                localMaintenanceNumber = rs.getString("localMaintenanceNumber");
                vendorPhoneNumber = rs.getString("vendorPhoneNumber");
                vendorEmail = rs.getString("vendorEmail");
                warrantyExpiration = rs.getString("warrantyExpiration");
                cameraStatus = rs.getString("cameraStatus");
                httpPort = rs.getInt("httpPort");
                onvifPort = rs.getInt("onvifPort");
                orgId = rs.getInt("orgId");
            }
        }

        return new CamerasBO(id, cameraName, feedIP, feedCredential, feedPassword, locationX, locationY, dateInstalled,
                floorID, vendorName, localMaintenanceName, localMaintenanceNumber, vendorPhoneNumber, vendorEmail,
                warrantyExpiration, cameraStatus, httpPort, onvifPort, orgId);
    }

    public List<CamerasBO> getAllCameras() throws Exception {

        int id;
        String cameraName;
        String feedIP;
        String feedCredential;
        String feedPassword ;
        double locationX;
        double locationY;
        Date dateInstalled;
        int floorID;
        String vendorName ;
        String localMaintenanceName ;
        String localMaintenanceNumber;
        String vendorPhoneNumber;
        String vendorEmail;
        String warrantyExpiration;
        String cameraStatus;
        int httpPort;
        int onvifPort;
        int orgId;

        List<CamerasBO> camerasBOList = new ArrayList<>();

        String query = "SELECT * FROM Cameras";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("cameraID");
                    cameraName = rs.getString("cameraName");
                    feedIP = rs.getString("feedIP");
                    feedCredential = rs.getString("feedCredential");
                    feedPassword = rs.getString("feedPassword");
                    locationX = rs.getDouble("locationX");
                    locationY = rs.getDouble("locationY");
                    dateInstalled = rs.getDate("dateInstalled");
                    floorID = rs.getInt("floorID");
                    vendorName = rs.getString("vendorName");
                    localMaintenanceName = rs.getString("localMaintenanceName");
                    localMaintenanceNumber = rs.getString("localMaintenanceNumber");
                    vendorPhoneNumber = rs.getString("vendorPhoneNumber");
                    vendorEmail = rs.getString("vendorEmail");
                    warrantyExpiration = rs.getString("warrantyExpiration");
                    cameraStatus = rs.getString("cameraStatus");
                    httpPort = rs.getInt("httpPort");
                    onvifPort = rs.getInt("onvifPort");
                    orgId = rs.getInt("orgId");

                    camerasBOList.add(new CamerasBO(id, cameraName, feedIP, feedCredential, feedPassword, locationX, locationY, dateInstalled,
                            floorID, vendorName, localMaintenanceName, localMaintenanceNumber, vendorPhoneNumber, vendorEmail,
                            warrantyExpiration, cameraStatus, httpPort, onvifPort, orgId));
                }
            }
        }

        return camerasBOList;
    }

    public CamerasBO getCameraByCameraId(int id) throws Exception {

        String cameraName;
        String feedIP;
        String feedCredential;
        String feedPassword ;
        double locationX;
        double locationY;
        Date dateInstalled;
        int floorID;
        String vendorName ;
        String localMaintenanceName ;
        String localMaintenanceNumber;
        String vendorPhoneNumber;
        String vendorEmail;
        String warrantyExpiration;
        String cameraStatus;
        int httpPort;
        int onvifPort;
        int orgId;

        String query = "SELECT * FROM Cameras WHERE cameraID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, Integer.toString(id));

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                id = rs.getInt("cameraID");
                cameraName = rs.getString("cameraName");
                feedIP = rs.getString("feedIP");
                feedCredential = rs.getString("feedCredential");
                feedPassword = rs.getString("feedPassword");
                locationX = rs.getDouble("locationX");
                locationY = rs.getDouble("locationY");
                dateInstalled = rs.getDate("dateInstalled");
                floorID = rs.getInt("floorID");
                vendorName = rs.getString("vendorName");
                localMaintenanceName = rs.getString("localMaintenanceName");
                localMaintenanceNumber = rs.getString("localMaintenanceNumber");
                vendorPhoneNumber = rs.getString("vendorPhoneNumber");
                vendorEmail = rs.getString("vendorEmail");
                warrantyExpiration = rs.getString("warrantyExpiration");
                cameraStatus = rs.getString("cameraStatus");
                httpPort = rs.getInt("httpPort");
                onvifPort = rs.getInt("onvifPort");
                orgId = rs.getInt("orgId");
            }

        }

        return new CamerasBO(id, cameraName, feedIP, feedCredential, feedPassword, locationX, locationY, dateInstalled,
                floorID, vendorName, localMaintenanceName, localMaintenanceNumber, vendorPhoneNumber, vendorEmail,
                warrantyExpiration, cameraStatus, httpPort,onvifPort, orgId);
    }

    public List<CamerasBO> getAllCamerasByFloorID(int _floorID) throws Exception {

        int id;
        String cameraName;
        String feedIP;
        String feedCredential;
        String feedPassword ;
        double locationX;
        double locationY;
        Date dateInstalled;
        int floorID;
        String vendorName ;
        String localMaintenanceName ;
        String localMaintenanceNumber;
        String vendorPhoneNumber;
        String vendorEmail;
        String warrantyExpiration;
        String cameraStatus;
        int httpPort;
        int onvifPort;
        int orgId;

        List<CamerasBO> camerasBOList = new ArrayList<>();

        String query = "SELECT * FROM Cameras WHERE floorID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, _floorID);

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("cameraID");
                    cameraName = rs.getString("cameraName");
                    feedIP = rs.getString("feedIP");
                    feedCredential = rs.getString("feedCredential");
                    feedPassword = rs.getString("feedPassword");
                    locationX = rs.getDouble("locationX");
                    locationY = rs.getDouble("locationY");
                    dateInstalled = rs.getDate("dateInstalled");
                    floorID = rs.getInt("floorID");
                    vendorName = rs.getString("vendorName");
                    localMaintenanceName = rs.getString("localMaintenanceName");
                    localMaintenanceNumber = rs.getString("localMaintenanceNumber");
                    vendorPhoneNumber = rs.getString("vendorPhoneNumber");
                    vendorEmail = rs.getString("vendorEmail");
                    warrantyExpiration = rs.getString("warrantyExpiration");
                    cameraStatus = rs.getString("cameraStatus");
                    httpPort = rs.getInt("httpPort");
                    onvifPort = rs.getInt("onvifPort");
                    orgId = rs.getInt("orgId");

                    camerasBOList.add(new CamerasBO(id, cameraName, feedIP, feedCredential, feedPassword, locationX, locationY, dateInstalled,
                            floorID, vendorName, localMaintenanceName, localMaintenanceNumber, vendorPhoneNumber, vendorEmail,
                            warrantyExpiration, cameraStatus, httpPort, onvifPort, orgId));
                }
            }
        }

        return camerasBOList;
    }

    public int getCameraCountByFloor(int _floorID) throws Exception {
        int count;

        String query = "SELECT COUNT(*) AS cameraCount FROM Cameras WHERE floorID = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, _floorID);

            try(ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                count = rs.getInt("cameraCount");
            }
        }
        return count;
    }

    public List<CamerasBO> getAllCamerasByOrgId(int _orgId) throws Exception {

        int id;
        String cameraName;
        String feedIP;
        String feedCredential;
        String feedPassword ;
        double locationX;
        double locationY;
        Date dateInstalled;
        int floorID;
        String vendorName ;
        String localMaintenanceName ;
        String localMaintenanceNumber;
        String vendorPhoneNumber;
        String vendorEmail;
        String warrantyExpiration;
        String cameraStatus;
        int httpPort;
        int onvifPort;
        int orgId;

        List<CamerasBO> camerasBOList = new ArrayList<>();

        String query = "SELECT * FROM Cameras WHERE orgId = ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, _orgId);

            try(ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    id = rs.getInt("cameraID");
                    cameraName = rs.getString("cameraName");
                    feedIP = rs.getString("feedIP");
                    feedCredential = rs.getString("feedCredential");
                    feedPassword = rs.getString("feedPassword");
                    locationX = rs.getDouble("locationX");
                    locationY = rs.getDouble("locationY");
                    dateInstalled = rs.getDate("dateInstalled");
                    floorID = rs.getInt("floorID");
                    vendorName = rs.getString("vendorName");
                    localMaintenanceName = rs.getString("localMaintenanceName");
                    localMaintenanceNumber = rs.getString("localMaintenanceNumber");
                    vendorPhoneNumber = rs.getString("vendorPhoneNumber");
                    vendorEmail = rs.getString("vendorEmail");
                    warrantyExpiration = rs.getString("warrantyExpiration");
                    cameraStatus = rs.getString("cameraStatus");
                    httpPort = rs.getInt("httpPort");
                    onvifPort = rs.getInt("onvifPort");
                    orgId = rs.getInt("orgId");

                    camerasBOList.add(new CamerasBO(id, cameraName, feedIP, feedCredential, feedPassword, locationX, locationY, dateInstalled,
                            floorID, vendorName, localMaintenanceName, localMaintenanceNumber, vendorPhoneNumber, vendorEmail,
                            warrantyExpiration, cameraStatus, httpPort, onvifPort, orgId));
                }
            }
        }

        return camerasBOList;
    }

    public void createCamera(CamerasBO camerasBO) throws Exception {

        String query = "INSERT INTO Cameras (cameraName, feedIP, feedCredential, feedPassword, locationX, locationY, dateInstalled, floorID, vendorName, localMaintenanceName, localMaintenanceNumber, vendorPhoneNumber, vendorEmail, warrantyExpiration, cameraStatus, httpPort, onvifPort, orgId)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, camerasBO.getCameraName());
            pstmt.setString(2, camerasBO.getFeedIP());
            pstmt.setString(3, camerasBO.getFeedCredential());
            pstmt.setString(4, camerasBO.getFeedPassword());
            pstmt.setDouble(5, camerasBO.getLocationX());
            pstmt.setDouble(6, camerasBO.getLocationY());
            pstmt.setDate(7, camerasBO.getDateInstalled());
            pstmt.setInt(8, camerasBO.getFloorID());
            pstmt.setString(9, camerasBO.getVendorName());
            pstmt.setString(10, camerasBO.getLocalMaintenanceName());
            pstmt.setString(11, camerasBO.getLocalMaintenanceNumber());
            pstmt.setString(12, camerasBO.getVendorPhoneNumber());
            pstmt.setString(13, camerasBO.getVendorEmail());
            pstmt.setString(14, camerasBO.getWarrantyExpiration());
            pstmt.setString(15, camerasBO.getCameraStatus());
            pstmt.setInt(16, camerasBO.getHttpPort());
            pstmt.setInt(17, camerasBO.getOnvifPort());
            pstmt.setInt(18, camerasBO.getOrgId());

            pstmt.executeUpdate();
        }
    }

    public void updateCameraById(int id, CamerasBO camerasBO) throws Exception {

        String query = "UPDATE Cameras SET cameraName=?, feedIP=?, feedCredential=?, feedPassword=?, locationX=?, locationY=?, dateInstalled=?, floorID=?, vendorName=?, localMaintenanceName=?, localMaintenanceNumber=?, vendorPhoneNumber=?, vendorEmail=?, warrantyExpiration=?, cameraStatus=?, httpPort=?, onvifPort=?, orgId=? WHERE cameraID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, camerasBO.getCameraName());
            pstmt.setString(2, camerasBO.getFeedIP());
            pstmt.setString(3, camerasBO.getFeedCredential());
            pstmt.setString(4, camerasBO.getFeedPassword());
            pstmt.setDouble(5, camerasBO.getLocationX());
            pstmt.setDouble(6, camerasBO.getLocationY());
            pstmt.setDate(7, camerasBO.getDateInstalled());
            pstmt.setInt(8, camerasBO.getFloorID());
            pstmt.setString(9, camerasBO.getVendorName());
            pstmt.setString(10, camerasBO.getLocalMaintenanceName());
            pstmt.setString(11, camerasBO.getLocalMaintenanceNumber());
            pstmt.setString(12, camerasBO.getVendorPhoneNumber());
            pstmt.setString(13, camerasBO.getVendorEmail());
            pstmt.setString(14, camerasBO.getWarrantyExpiration());
            pstmt.setString(15, camerasBO.getCameraStatus());
            pstmt.setInt(16, camerasBO.getHttpPort());
            pstmt.setInt(17, camerasBO.getOnvifPort());
            pstmt.setInt(18, camerasBO.getOrgId());
            pstmt.setInt(19, id);

            pstmt.executeUpdate();
        }
    }

    public void deleteCameraById(int id) throws Exception {

        String query = "DELETE FROM Cameras WHERE cameraID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        }
    }
}
