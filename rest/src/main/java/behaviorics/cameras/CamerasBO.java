package behaviorics.cameras;

import java.sql.Date;

public class CamerasBO {
    private int id;
    private String cameraName;
    private String feedIP;
    private String feedCredential;
    private String feedPassword ;
    private double locationX;
    private double locationY;
    private Date dateInstalled;
    private int floorID;
    private String vendorName ;
    private String localMaintenanceName ;
    private String localMaintenanceNumber;
    private String vendorPhoneNumber;
    private String vendorEmail;
    private String warrantyExpiration;
    private String cameraStatus;
    private int httpPort;
    private int onvifPort;
    private int orgId;

    public CamerasBO(){}

    public CamerasBO(int _id, String _cameraName, String _feedIP, String _feedCredential, String _feedPassword , double _locationX,
                     double _locationY, Date _dateInstalled, int _floorID, String _vendorName , String _localMaintenanceName,
                     String _localMaintenanceNumber, String _vendorPhoneNumber, String _vendorEmail , String _warrantyExpiration,
                     String _cameraStatus, int _httpPort, int _onvifPort, int _orgId) {
        id = _id;
        cameraName = _cameraName;
        feedIP = _feedIP;
        feedCredential = _feedCredential;
        feedPassword = _feedPassword;
        locationX = _locationX;
        locationY = _locationY;
        dateInstalled = _dateInstalled;
        floorID = _floorID;
        vendorName = _vendorName;
        localMaintenanceName = _localMaintenanceName;
        localMaintenanceNumber = _localMaintenanceNumber;
        vendorPhoneNumber = _vendorPhoneNumber;
        vendorEmail = _vendorEmail;
        warrantyExpiration = _warrantyExpiration;
        cameraStatus = _cameraStatus;
        httpPort = _httpPort;
        onvifPort = _onvifPort;
        orgId = _orgId;
    }

    public int getId() {
        return id;
    }

    public String getCameraName() {
        return cameraName;
    }

    public String getFeedIP() {
        return feedIP;
    }

    public String getFeedCredential() {
        return feedCredential;
    }

    public String getFeedPassword() {
        return feedPassword;
    }

    public double getLocationX() {
        return locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public Date getDateInstalled() {
        return dateInstalled;
    }

    public int getFloorID() {
        return floorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getLocalMaintenanceName() {
        return localMaintenanceName;
    }

    public String getLocalMaintenanceNumber() {
        return localMaintenanceNumber;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public String getWarrantyExpiration() {
        return warrantyExpiration;
    }

    public String getCameraStatus() {
        return cameraStatus;
    }

    public int getOrgId() { return orgId; }

    public void setCameraName(String _cameraName) { cameraName = _cameraName; }
    @Override
    public String toString() {
        return "CamerasBO{" +
                "id=" + id +
                ", cameraName='" + cameraName + '\'' +
                ", feedIP='" + feedIP + '\'' +
                ", feedCredential='" + feedCredential + '\'' +
                ", feedPassword='" + feedPassword + '\'' +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", dateInstalled='" + dateInstalled + '\'' +
                ", floorID=" + floorID +
                ", vendorName='" + vendorName + '\'' +
                ", localMaintenanceName='" + localMaintenanceName + '\'' +
                ", localMaintenanceNumber='" + localMaintenanceNumber + '\'' +
                ", vendorPhoneNumber='" + vendorPhoneNumber + '\'' +
                ", vendorEmail='" + vendorEmail + '\'' +
                ", warrantyExpiration='" + warrantyExpiration + '\'' +
                ", cameraStatus='" + cameraStatus + '\'' +
                ", httpPort='" + httpPort + '\'' +
                ", onvifPort='" + onvifPort + '\'' +
                ", orgId=" + orgId +
                '}';
    }

    public int getHttpPort() {
        return httpPort;
    }

    public int getOnvifPort() {
        return onvifPort;
    }
}
