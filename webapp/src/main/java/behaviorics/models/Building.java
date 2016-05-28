package behaviorics.models;

public class Building {
    private int id;
    private String buildingName;
    private int entityID;
    private String entityName;
    private int streetCode;
    private String streetName;
    private String city;
    private int zipcode;
    private String buildingAcronym;
    private String state;

    public Building(){}

    public Building(int _id, String _buildingName, int _entityID, int _streetCode, String _streetName, String _city, int _zipcode, String _buildingAcronym, String _state) {
        id = _id;
        buildingName = _buildingName;
        entityID = _entityID;
        streetCode = _streetCode;
        streetName = _streetName;
        city = _city;
        zipcode = _zipcode;
        buildingAcronym = _buildingAcronym;
        state = _state;
    }

    public Building(int _id, String _buildingName, int _entityID, String _entityName, int _streetCode, String _streetName, String _city, int _zipcode, String _buildingAcronym, String _state) {
        id = _id;
        buildingName = _buildingName;
        entityID = _entityID;
        entityName = _entityName;
        streetCode = _streetCode;
        streetName = _streetName;
        city = _city;
        zipcode = _zipcode;
        buildingAcronym = _buildingAcronym;
        state = _state;
    }

    // setter methods are needed for the Thymeleaf form submission

    public int getId() {return id;}

    public void setId(int _id) {this.id = _id;}

    public String getBuildingName() {return buildingName;}

    public void setBuildingName(String _buildingName) {this.buildingName = _buildingName;}

    public int getEntityID() {return entityID;}

    public void setEntityID(int _entityID) {this.entityID = _entityID;}

    public int getStreetCode() {return streetCode;}

    public void setStreetCode(int _streetCode) {this.streetCode = _streetCode;}

    public String getStreetName() {return streetName;}

    public void setStreetName(String _streetName) {this.streetName = _streetName;}

    public String getCity() {return city;}

    public void setCity(String _city) {this.city = _city;}

    public int getZipcode() {return zipcode;}

    public void setZipcode(int _zipcode) {this.zipcode = _zipcode;}

    public String getBuildingAcronym() {return buildingAcronym;}

    public void setBuildingAcronym(String _buildingAcronym) {this.buildingAcronym = _buildingAcronym;}

    public String getState() {return state;}

    public void setState(String _state) {this.state = _state;}

    @Override
    public String toString() {
        return "id: " + id + ", "
                + "buildingName: " + buildingName + ", "
                + "entityId: " + entityID + ", "
                + "streetCode: " + streetCode + ", "
                + "streetName: " + streetName + ", "
                + "city: " + city + ", "
                + "zipcode: " + zipcode + ", "
                + "buildingAcronym: " + buildingAcronym + ", "
                + "state: " + state;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
