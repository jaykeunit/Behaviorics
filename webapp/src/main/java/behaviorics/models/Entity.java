package behaviorics.models;

public class Entity {
    private int id;
    private int organizationID;
    private String organizationName;
    private String entityName;
    private String contactNumber;
    private String entityAcronym;

    public Entity(){

    }

    public Entity(int _id, int _organizationID, String _entityName, String _contactNumber, String _entityAcronym) {
        id = _id;
        organizationID = _organizationID;
        entityName = _entityName;
        contactNumber = _contactNumber;
        entityAcronym = _entityAcronym;

    }

    public Entity(int _id, int _organizationID, String _organizationName, String _entityName, String _contactNumber, String _entityAcronym) {
        id = _id;
        organizationID = _organizationID;
        organizationName = _organizationName;
        entityName = _entityName;
        contactNumber = _contactNumber;
        entityAcronym = _entityAcronym;

    }

    // setter methods are needed for the Thymeleaf form submission

    public int getID() {
        return id;
    }

    public void setID(int _id) { this.id = _id; }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int _organizationID) { this.organizationID = _organizationID; }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String _entityName) { this.entityName = _entityName; }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String _contactNumber) { this.contactNumber = _contactNumber; }

    public String getEntityAcronym() {
        return entityAcronym;
    }

    public void setEntityAcronym(String _entityAcronym) { this.entityAcronym = _entityAcronym; }

    @Override
    public String toString(){
        return "id: " + id + ", "
                + "organizationID: " + organizationID + ", "
                + "entityName: " + entityName + ", "
                + "contactNumber: " + contactNumber + ", "
                + "entityAcronym: " + entityAcronym;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}