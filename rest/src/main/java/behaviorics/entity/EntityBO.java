package behaviorics.entity;

public class EntityBO {
    private int id;
    private int organizationID;
    private String organizationName;
    private String entityName;
    private String contactNumber;
    private String entityAcronym;

    public EntityBO(){

    }

    public EntityBO(int _id, int _organizationID, String _entityName, String _contactNumber, String _entityAcronym) {
        id = _id;
        organizationID = _organizationID;
        entityName = _entityName;
        contactNumber = _contactNumber;
        entityAcronym = _entityAcronym;

    }

    public EntityBO(int _id, int _organizationID, String _organizationName, String _entityName, String _contactNumber, String _entityAcronym) {
        id = _id;
        organizationID = _organizationID;
        organizationName = _organizationName;
        entityName = _entityName;
        contactNumber = _contactNumber;
        entityAcronym = _entityAcronym;

    }

    public int getID() {
        return id;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEntityAcronym() {
        return entityAcronym;
    }

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