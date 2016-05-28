package behaviorics.floors;

public class FloorBO {
    private int id;
    private int floorNumber;
    private int buildingID;
    private String buildingName;
    private String floorType;
    private String nickname;

    public FloorBO() {

    }

    public FloorBO(int _id, int _buildingID, int _floorNumber, String _floorType, String _nickname) {
        id = _id;
        buildingID  = _buildingID;
        floorNumber = _floorNumber;
        floorType = _floorType;
        nickname = _nickname;
    }

    public FloorBO(int _id, int _buildingID, String _buildingName, int _floorNumber, String _floorType, String _nickname) {
        id = _id;
        buildingID  = _buildingID;
        buildingName = _buildingName;
        floorNumber = _floorNumber;
        floorType = _floorType;
        nickname = _nickname;
    }

    public int getID() { return id; }

    public int getFloorNumber() { return floorNumber; }

    public int getBuildingID() { return buildingID; }

    public String getFloorType() { return floorType; }

    public String getNickname() { return nickname; }

    // Needed for concatenation
    public void setNickname(String _nickname) { nickname = _nickname; }

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
