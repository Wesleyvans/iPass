package nl.hu.v1c.project.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OefeningDaoImpl extends PostgresBaseDao implements OefeningDao {

	@Override
	public boolean saveOefeningWorkout(int workout_id, int oefening_id) throws SQLException {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO \"Workout_Oefening\" VALUES(?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, workout_id);
			pstmt.setInt(2, oefening_id);
			pstmt.executeUpdate();
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}
	
	public boolean saveOefening(String oefening) throws SQLException {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO \"Oefening\" VALUES(?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, oefening);
			pstmt.executeUpdate();
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Oefening> findOefeningenByCode(String query) throws SQLException {
		List<Oefening> results = new ArrayList<Oefening>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int workout_id = dbResultSet.getInt("workout_id");
				int oefening_id = dbResultSet.getInt("oefening_id");
				String oefening = dbResultSet.getString("oefening");
				
			results.add(new Oefening(workout_id, oefening_id, oefening));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	@Override
	public List<Oefening> findOefeningen(String query) throws SQLException {
		List<Oefening> results = new ArrayList<Oefening>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int oefening_id = dbResultSet.getInt("oefening_id");
				String oefening = dbResultSet.getString("oefening");
				
			results.add(new Oefening(oefening_id, oefening));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}

	@Override
	public List<Oefening> findAllByCode(int workout_id) throws SQLException {
		return findOefeningenByCode("SELECT wo.workout_id, o.oefening_id, o.oefening FROM \"Workout_Oefening\" wo JOIN \"Oefening\" o ON wo.oefening_id = o.oefening_id WHERE wo.workout_id = " + workout_id +"");
	}
	
	public List<Oefening> findAll() throws SQLException {
		return findOefeningen("SELECT oefening_id, oefening FROM \"Oefening\"");
	}



}
