package behaviorics.building;

public class BuildingBO {
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

    public BuildingBO(){}

    public BuildingBO(int _id, String _buildingName, int _entityID, int _streetCode, String _streetName, String _city, int _zipcode, String _buildingAcronym, String _state) {
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

    public BuildingBO(int _id, String _buildingName, int _entityID, String _entityName, int _streetCode, String _streetName, String _city, int _zipcode, String _buildingAcronym, String _state) {
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

    public int getId() {return id;}

    public String getBuildingName() {return buildingName;}

    public int getEntityID() {return entityID;}

    public int getStreetCode() {return streetCode;}

    public String getStreetName() {return streetName;}

    public String getCity() {return city;}

    public int getZipcode() {return zipcode;}

    public String getBuildingAcronym() {return buildingAcronym;}

    public String getState() {return state;}

    // needed for acronym concatenation
    public void setBuildingAcronym(String acronym) { buildingAcronym = acronym; }

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
