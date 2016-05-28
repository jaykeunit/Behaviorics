package behaviorics.models;

public class Organization {

    private int id;
    private String organizationName;

    public Organization() {
    }

    public Organization(int id, String organizationName) {
        this.id = id;
        this.organizationName = organizationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                '}';
    }
}
