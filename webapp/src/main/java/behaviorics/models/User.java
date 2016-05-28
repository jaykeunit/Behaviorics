package behaviorics.models;

import java.util.Arrays;

public class User {

    private String userName;
    private String privilege;
    private int organizationID;
    private String organizationName;
    private byte[] salt;
    private byte[] hash;
    private int id;
    private String key;

    public User(){}

    public User(int _id, String _userName, String _privilege, int _organizationID, String _organizationName){
        id = _id;
        userName = _userName;
        privilege = _privilege;
        organizationID = _organizationID;
        organizationName = _organizationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "id: "+ id + " "
                + "userName: "+ userName + " "
                + "key: "+ key + " "
                + "salt: " + Arrays.toString(salt) + " "
                + "hash: " + Arrays.toString(hash) + " "
                + "privilege: "+ privilege + " "
                + "organizationID: "+ organizationID + " "
                + "organizationName: "+ organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}