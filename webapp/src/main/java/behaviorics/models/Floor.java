package behaviorics.models;

public class Floor {
    private int id;
    private int floorNumber;
    private int buildingID;
    private String buildingName;
    private String floorType;
    private String nickname;

    public Floor() {

    }

    public Floor(int _id, int _buildingID, int _floorNumber, String _floorType, String _nickname) {
        id = _id;
        buildingID  = _buildingID;
        floorNumber = _floorNumber;
        floorType = _floorType;
        nickname = _nickname;
    }

    public Floor(int _id, int _buildingID, String _buildingName, int _floorNumber, String _floorType, String _nickname) {
        id = _id;
        buildingID  = _buildingID;
        buildingName = _buildingName;
        floorNumber = _floorNumber;
        floorType = _floorType;
        nickname = _nickname;
    }

    // setter methods are required for Thymeleaf form submission

    public int getID() { return id; }

    public void setID(int _id) { this.id = _id; }

    public int getFloorNumber() { return floorNumber; }

    public void setFloorNumber(int _floorNumber) { this.floorNumber = _floorNumber; }

    public int getBuildingID() { return buildingID; }

    public void setBuildingID(int _buildingID) { this.buildingID = _buildingID; }

    public String getFloorType() { return floorType; }

    public void setFloorType(String _floorType) { this.floorType = _floorType; }

    public String getNickname() { return nickname; }

    public void setNickname(String _nickname) { this.nickname = _nickname; }

    @Override
    public String toString() {
        return "id: " + id + " "
                + "buildingID: " + buildingID + " "
                + "floorNumber: " + floorNumber + " "
                + "floorType: " + floorType + " "
                + "nickname: " + nickname + " ";
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
