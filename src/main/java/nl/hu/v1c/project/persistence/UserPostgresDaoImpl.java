package nl.hu.v1c.project.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {

	public String findRoleForUser(String username, String password) {
		String role = null;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT r.rol FROM \"Persoon\" p JOIN \"Rol\" r ON p.rol_id = r.rol_id  WHERE gebruikersnaam = '" + username + "' "
					+ "AND wachtwoord = '" + password + "'";
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			if(dbResultSet != null) {
				while (dbResultSet.next()) {
					role = dbResultSet.getString("rol");
				}
			}
		}
		catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return role;
		
	}

}
