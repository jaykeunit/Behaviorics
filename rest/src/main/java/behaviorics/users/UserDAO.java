package behaviorics.users;

import behaviorics.BehavioricsDatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	public UserBO getUserByUserName(String name)throws Exception {
		
		int id;
		String username;
		byte[] salt;
        byte[] hash;
        String privilege;
        int organizationID;

		String query = "SELECT * FROM Users WHERE userName=?";

		try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
			PreparedStatement pstmt = dbc.prepareStatement(query)) {
			pstmt.setString(1, name);

			try(ResultSet rs = pstmt.executeQuery()) {
				rs.next();
				id = rs.getInt("usersID");
				username = rs.getString("userName");
				int saltLength = Math.toIntExact(rs.getBlob("salt").length());
				int hashLength = Math.toIntExact(rs.getBlob("hash").length());
				salt = rs.getBlob("salt").getBytes(1, saltLength);
				hash = rs.getBlob("hash").getBytes(1, hashLength);
				privilege = rs.getString("privilege");
				organizationID = rs.getInt("organizationID");
			}
		}
    	return new UserBO(id, username, salt, hash, privilege, organizationID);
	}

	public List<UserBO> getAllUserByOrganizationID(int organizationID)throws Exception {

		int id;
		String username;
		String privilege;
		String organizationName;

		List<UserBO> userList = new ArrayList<>();

		String query = "SELECT Users.usersID, Users.username, Users.privilege, Users.organizationID, Organization.organizationName\n" +
				"FROM Users\n" +
				"inner join Organization\n" +
				"\ton  Users.organizationID = Organization.organizationID\n" +
				"where Organization.organizationID = ?";

		try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
			PreparedStatement pstmt = dbc.prepareStatement(query)) {
			pstmt.setInt(1, organizationID);

			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					id = rs.getInt("usersID");
					username = rs.getString("userName");
					privilege = rs.getString("privilege");
					organizationID = rs.getInt("organizationID");
					organizationName = rs.getString("organizationName");
					userList.add(new UserBO(id, username, privilege, organizationID, organizationName));
				}
			}
		}
		return userList;
	}

    public void createUser(UserBO userBo) throws Exception{

		String query = "INSERT INTO Users (username,hash,salt,privilege,organizationID) VALUES (?,?,?,?,?)";

		try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
			PreparedStatement pstmt = dbc.prepareStatement(query)) {
			pstmt.setString(1, userBo.getUserName());
			pstmt.setBytes(2, userBo.getHash());
			pstmt.setBytes(3, userBo.getSalt());
			pstmt.setString(4, userBo.getPrivilege());
			pstmt.setInt(5, userBo.getOrganizationID());

			pstmt.executeUpdate();
		}
	}
    
    public void updateUserById(int id, UserBO userBo) throws Exception{

		String query = "UPDATE Users SET userName=?, privilege=?, organizationID=? WHERE usersID =?";

		try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
			PreparedStatement pstmt = dbc.prepareStatement(query)) {
			pstmt.setString(1, userBo.getUserName());
			pstmt.setString(2, userBo.getPrivilege());
			pstmt.setInt(3, userBo.getOrganizationID());
			pstmt.setInt(4, id);

			pstmt.executeUpdate();
		}
	}
    
    public void deleteUserById(int id) throws Exception{

		String query = "DELETE FROM Users WHERE usersID = ?";

		try(BehavioricsDatabaseConnection dbc = new BehavioricsDatabaseConnection();
			PreparedStatement pstmt = dbc.prepareStatement(query)) {
			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		}
	}
}
