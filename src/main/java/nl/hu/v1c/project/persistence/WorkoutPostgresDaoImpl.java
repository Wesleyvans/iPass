package nl.hu.v1c.project.persistence;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPostgresDaoImpl extends PostgresBaseDao implements WorkoutDao {

	@Override
	public int saveWorkout(String titel, String beschrijving, int categorie_id, String gebruikersnaam) throws SQLException {
		int result = 0;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO \"Workout\" VALUES(?,?,(SELECT persoon_id FROM \"Persoon\" WHERE gebruikersnaam = '" +gebruikersnaam+ "'),?)";
			PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, titel);
			pstmt.setString(2, beschrijving);
			pstmt.setInt(3, categorie_id);
			pstmt.executeUpdate();
			ResultSet keys = pstmt.getGeneratedKeys();
			if(keys.next()) {
				result = keys.getInt("workout_id");
				System.out.println(keys.getInt(1));
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Workout> findWorkouts(String query) throws SQLException {
		List<Workout> results = new ArrayList<Workout>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("workout_id");
				String titel = dbResultSet.getString("titel");
				String beschrijving = dbResultSet.getString("beschrijving");
				String gebruikersnaam = dbResultSet.getString("gebruikersnaam");
				String categorie = dbResultSet.getString("categorie");
				
			results.add(new Workout(id, titel, beschrijving, gebruikersnaam, categorie));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	@Override
	public List<Workout> findAll() throws SQLException {
		return findWorkouts("SELECT w.workout_id, w.titel, w.beschrijving, p.gebruikersnaam, c.categorie FROM \"Workout\" w JOIN \"Persoon\" p ON w.persoon_id = p.persoon_id JOIN \"Categorie\" c ON w.categorie_id = c.categorie_id");
	}

	@Override
	public Workout findByCode(int code) throws SQLException {
		List<Workout> workouts = findWorkouts("SELECT titel, beschrijving, persoon_id, categorie_id FROM Workout WHERE workout_id = " + code + "");
		if(!workouts.isEmpty()) {
			return workouts.get(0);
		}
		return null;
	}

	@Override
	public boolean Update(int code, String titel, String beschrijving, int categorie) throws SQLException {
		boolean result = false;
		boolean workoutExists = findByCode(code) != null;
			
		if (workoutExists) {
			String query = "UPDATE workout SET titel = '" + titel + "', beschrijving = '" + beschrijving +
							"', categorie_id = " + categorie  + " WHERE workout_id = " + code + "";
			
			try (Connection con = super.getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public boolean Delete(int id) throws SQLException {
		boolean result = false;
		
		String query = "DELETE FROM Workout WHERE workout_id = " + id + ""; 
				
		try (Connection con = super.getConnection()) {
			
			Statement stmt = con.createStatement();
			if (stmt.executeUpdate(query) == 1) { // 1 row updated!
				result = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
	}


}
