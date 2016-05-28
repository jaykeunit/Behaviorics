package behaviorics.organization;

public class OrganizationBO {
    private int id;
    private String organizationName;

    public OrganizationBO() {

    }

    public OrganizationBO(int id, String organizationName) {
        this.id = id;
        this.organizationName = organizationName;
    }

    public int getId() {
        return id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    @Override
    public String toString() {
        return "id: " + id + ", "
                + "organizationName: " + organizationName;
    }
}
