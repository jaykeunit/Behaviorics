package behaviorics.SpecialCaseQueries;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;
import behaviorics.cameras.CamerasBO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialCaseQueriesDAO {

    public List<MonitorCamerasBO> getOrganizationToFloorsList() throws Exception {

        int organizationID;
        int entityID;
        String entityName;
        int buildingID;
        String buildingName;
        int floorID;
        int floorNumber;

        List<MonitorCamerasBO> monitorCamerasBOList = new ArrayList<>();

        String query = "select Organization.organizationID, Entity.entityID, Entity.entityName, \n" +
                "Building.buildingID, Building.buildingName, Floors.floorID, Floors.floorNumber\n" +
                "From Organization\n" +
                "inner join Entity\n" +
                "\ton Organization.organizationID = Entity.organizationID\n" +
                "inner join Building\n" +
                "\ton Entity.entityID = Building.entityID\n" +
                "inner join Floors\n" +
                "\ton Building.buildingID = Floors.buildingID\n" +
                "ORDER BY Entity.entityName, Building.buildingName, Floors.floorNumber";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    organizationID = rs.getInt("organizationID");
                    entityID = rs.getInt("entityID");
                    entityName = rs.getString("entityName");
                    buildingID = rs.getInt("buildingID");
                    buildingName = rs.getString("buildingName");
                    floorID = rs.getInt("floorID");
                    floorNumber = rs.getInt("floorNumber");

                    monitorCamerasBOList.add(new MonitorCamerasBO(organizationID, entityID, entityName, buildingID, buildingName, floorID, floorNumber));
                }
            }
        }

        return monitorCamerasBOList;
    }

    public List<EditCameraBO> getAllCamerasById(int organizationID) throws Exception {

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
        int orgId = organizationID;
        String entityName;
        String buildingName;
        int floorNumber;

        List<EditCameraBO> editCamerasBOList = new ArrayList<>();

        String query = "select Cameras.*, Floors.floorNumber, Building.buildingName, Entity.entityName\n" +
                "From Cameras\n" +
                "inner join Floors\n" +
                "\ton Cameras.floorID = Floors.floorID\n" +
                "inner join Building\n" +
                "\ton Floors.buildingID = Building.buildingID\n" +
                "inner join Entity\n" +
                "\ton Building.entityID = Entity.entityID\n" +
                "inner Join Organization\n" +
                "\ton Entity.organizationID = Organization.organizationID\n" +
                "Where Organization.organizationID = ?\n" +
                "order by Entity.entityName, Building.buildingName, Floors.floorNumber";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {

            pstmt.setInt(1, organizationID);

            try(ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("cameraID");
                    cameraName = rs.getString("cameraName");
                    feedIP = rs.getString("feedIP");
                    feedCredential = rs.getString("feedCredential");
                    feedPassword = rs.getString("feedPassword");
                    locationX = rs.getInt("locationX");
                    locationY = rs.getInt("locationY");
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
                    entityName = rs.getString("entityName");
                    buildingName = rs.getString("buildingName");
                    floorNumber = rs.getInt("floorNumber");

                    editCamerasBOList.add(new EditCameraBO(id, cameraName, feedIP, feedCredential, feedPassword ,
                            locationX, locationY, dateInstalled, floorID, vendorName , localMaintenanceName,
                            localMaintenanceNumber, vendorPhoneNumber, vendorEmail , warrantyExpiration,
                            cameraStatus, httpPort, onvifPort, orgId, entityName, buildingName, floorNumber));
                }
            }
        }
        return editCamerasBOList;
    }

    public void updateCameraById(int id, EditCameraBO editCameraBO) throws Exception {

        String query = "UPDATE Cameras SET cameraName=?, feedIP=?, feedCredential=?, feedPassword=?, locationX=?, locationY=?, dateInstalled=?, floorID=?, vendorName=?, localMaintenanceName=?, localMaintenanceNumber=?, vendorPhoneNumber=?, vendorEmail=?, warrantyExpiration=?, cameraStatus=?, httpPort=?, onvifPort=? WHERE cameraID= ?";

        try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
            PreparedStatement pstmt = dbc.prepareStatement(query)) {
            pstmt.setString(1, editCameraBO.getCameraName());
            pstmt.setString(2, editCameraBO.getFeedIP());
            pstmt.setString(3, editCameraBO.getFeedCredential());
            pstmt.setString(4, editCameraBO.getFeedPassword());
            pstmt.setDouble(5, editCameraBO.getLocationX());
            pstmt.setDouble(6, editCameraBO.getLocationY());
            pstmt.setDate(7, editCameraBO.getDateInstalled());
            pstmt.setInt(8, editCameraBO.getFloorID());
            pstmt.setString(9, editCameraBO.getVendorName());
            pstmt.setString(10, editCameraBO.getLocalMaintenanceName());
            pstmt.setString(11, editCameraBO.getLocalMaintenanceNumber());
            pstmt.setString(12, editCameraBO.getVendorPhoneNumber());
            pstmt.setString(13, editCameraBO.getVendorEmail());
            pstmt.setString(14, editCameraBO.getWarrantyExpiration());
            pstmt.setString(15, editCameraBO.getCameraStatus());
            pstmt.setInt(16, editCameraBO.getHttpPort());
            pstmt.setInt(17, editCameraBO.getOnvifPort());
            pstmt.setInt(18, id);

            pstmt.executeUpdate();
        }
    }
}
