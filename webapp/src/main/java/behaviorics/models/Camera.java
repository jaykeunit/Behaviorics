package behaviorics.models;

import java.sql.Date;

public class Camera {

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

    public Camera(){}

    public Camera(int _id, String _cameraName, String _feedIP, String _feedCredential, String _feedPassword , double _locationX,
                     double _locationY, Date _dateInstalled, int _floorID, String _vendorName , String _localMaintenanceName,
                     String _localMaintenanceNumber, String _vendorPhoneNumber, String _vendorEmail , String _warrentyExpiration,
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
        warrantyExpiration = _warrentyExpiration;
        cameraStatus = _cameraStatus;
        httpPort = _httpPort;
        onvifPort = _onvifPort;
        orgId = _orgId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getFeedIP() {
        return feedIP;
    }

    public void setFeedIP(String feedIP) {
        this.feedIP = feedIP;
    }

    public String getFeedCredential() {
        return feedCredential;
    }

    public void setFeedCredential(String feedCredential) {
        this.feedCredential = feedCredential;
    }

    public String getFeedPassword() {
        return feedPassword;
    }

    public void setFeedPassword(String feedPassword) {
        this.feedPassword = feedPassword;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public Date getDateInstalled() {
        return dateInstalled;
    }

    public void setDateInstalled(Date dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public int getFloorID() {
        return floorID;
    }

    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getLocalMaintenanceName() {
        return localMaintenanceName;
    }

    public void setLocalMaintenanceName(String localMaintenanceName) {
        this.localMaintenanceName = localMaintenanceName;
    }

    public String getLocalMaintenanceNumber() {
        return localMaintenanceNumber;
    }

    public void setLocalMaintenanceNumber(String localMaintenanceNumber) {
        this.localMaintenanceNumber = localMaintenanceNumber;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getWarrantyExpiration() {
        return warrantyExpiration;
    }

    public void setWarrantyExpiration(String warrantyExpiration) {
        this.warrantyExpiration = warrantyExpiration;
    }

    public String getCameraStatus() {
        return cameraStatus;
    }

    public Integer getOrgId() { return orgId; }

    public void setCameraStatus(String cameraStatus) {
        this.cameraStatus = cameraStatus;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public int getOnvifPort() {
        return onvifPort;
    }

    public void setOnvifPort(int onvifPort) {
        this.onvifPort = onvifPort;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

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
                //", dateInstalled='" + dateInstalled + '\'' +
                ", floorID=" + floorID +
                ", vendorName='" + vendorName + '\'' +
                ", localMaintenanceName='" + localMaintenanceName + '\'' +
                ", localMaintenanceNumber='" + localMaintenanceNumber + '\'' +
                ", vendorPhoneNumber='" + vendorPhoneNumber + '\'' +
                ", vendorEmail='" + vendorEmail + '\'' +
                ", warrantyExpiration='" + warrantyExpiration + '\'' +
                ", cameraStatus='" + cameraStatus + '\'' +
                ", httpPort='" + httpPort + '\'' +
                ", mediaPort='" + onvifPort + '\'' +
                ", orgId=" + orgId +
                '}';
    }

}
