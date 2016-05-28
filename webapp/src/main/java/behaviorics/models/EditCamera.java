package behaviorics.models;

import java.sql.Date;

public class EditCamera {

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
    private String entityName;
    private String buildingName;
    private int floorNumber;

    public EditCamera(){}

    public EditCamera(int _id, String _cameraName, String _feedIP, String _feedCredential, String _feedPassword , double _locationX,
                        double _locationY, Date _dateInstalled, int _floorID, String _vendorName , String _localMaintenanceName,
                        String _localMaintenanceNumber, String _vendorPhoneNumber, String _vendorEmail , String _warrantyExpiration,
                        String _cameraStatus, int _httpPort, int _onvifPort, int _orgId, String _entityName, String _buildingName, int _floorNumber) {
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
        entityName = _entityName;
        buildingName = _buildingName;
        floorNumber = _floorNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public void setFeedIP(String feedIP) {
        this.feedIP = feedIP;
    }

    public void setFeedCredential(String feedCredential) {
        this.feedCredential = feedCredential;
    }

    public void setFeedPassword(String feedPassword) {
        this.feedPassword = feedPassword;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public void setDateInstalled(Date dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setLocalMaintenanceName(String localMaintenanceName) {
        this.localMaintenanceName = localMaintenanceName;
    }

    public void setLocalMaintenanceNumber(String localMaintenanceNumber) {
        this.localMaintenanceNumber = localMaintenanceNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public void setWarrantyExpiration(String warrantyExpiration) {
        this.warrantyExpiration = warrantyExpiration;
    }

    public void setCameraStatus(String cameraStatus) {
        this.cameraStatus = cameraStatus;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public void setOnvifPort(int onvifPort) {
        this.onvifPort = onvifPort;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
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

    public int getHttpPort() {
        return httpPort;
    }

    public int getOnvifPort() {
        return onvifPort;
    }

    public int getOrgId() {
        return orgId;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public int getFloorNumber() {
        return floorNumber;
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
                ", onvifPort='" + onvifPort + '\'' +
                ", orgId=" + orgId +
                ", entityName='" + entityName + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", floorNumber=" + floorNumber +
                '}';
    }

}