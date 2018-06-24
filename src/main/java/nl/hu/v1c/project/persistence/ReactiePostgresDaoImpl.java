package nl.hu.v1c.project.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReactiePostgresDaoImpl extends PostgresBaseDao implements ReactieDao {

	@Override
	public boolean saveReactie(String gebruikersnaam, String reactie, int workout_id) throws SQLException {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO \"Reactie\" VALUES(?,(SELECT persoon_id FROM \"Persoon\" WHERE gebruikersnaam = '"+gebruikersnaam+"'),?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, reactie);
			pstmt.setInt(2, workout_id);
			pstmt.executeUpdate();
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Reactie> findReacties(String query) throws SQLException {
		List<Reactie> results = new ArrayList<Reactie>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				String reactie = dbResultSet.getString("reactie");
				String persoon = dbResultSet.getString("gebruikersnaam");
				int workout_id = dbResultSet.getInt("workout_id");
				
			results.add(new Reactie(reactie, persoon, workout_id));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}

	@Override
	public List<Reactie> findAll(int workout_id) throws SQLException {
		return findReacties("SELECT r.reactie, p.gebruikersnaam, r.workout_id FROM \"Reactie\" r JOIN \"Persoon\" p ON r.persoon_id = p.persoon_id WHERE workout_id = " + workout_id + "");
	}




}
