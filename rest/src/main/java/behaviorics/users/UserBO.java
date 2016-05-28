package behaviorics.users;

import java.util.Arrays;

public class UserBO {
	private int id;
	private String userName;
	private byte[] salt;
    private byte[] hash;
    private String privilege;
    private int organizationID;
	private String organizationName;
    
    public UserBO(){
        
    }

	public UserBO(int _id, String _userName, byte[] _salt, byte[] _hash, String _privilege, int _organizationID){
		id = _id;
		userName = _userName;
		salt = _salt;
        hash = _hash;
        privilege = _privilege;
        organizationID = _organizationID;
	}

	public UserBO(int _id, String _userName, String _privilege, int _organizationID, String _organizationName){
		id = _id;
		userName = _userName;
		privilege = _privilege;
		organizationID = _organizationID;
		organizationName = _organizationName;
	}
    
	public int getId(){
		return id;
	}

	public String getUserName(){
		return userName;
	}

	public byte[] getSalt(){
		return salt;
	}
    
    public byte[] getHash(){
		return hash;
	}
    
    public String getPrivilege(){
		return privilege;
	}
    
    public int getOrganizationID(){
		return organizationID;
	}
    
    @Override
    public String toString(){
        return "id: "+ id + " " 
        + "userName: "+ userName + " "
        + "salt: " + Arrays.toString(salt) + " "
        + "hash: " + Arrays.toString(hash) + " "
        + "privilege: "+ privilege + " " 
        + "organizationID: "+ organizationID;
    }

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
}