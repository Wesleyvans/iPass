package nl.hu.v1c.project.persistence;


import java.sql.SQLException;
import java.util.List;

public interface OefeningDao {
	public boolean saveOefeningWorkout(int workout_id, int oefening_id) throws SQLException;
	
	public List<Oefening> findOefeningenByCode(String query) throws SQLException;
	
	public List<Oefening> findOefeningen(String query) throws SQLException;
	
	public List<Oefening> findAllByCode(int workout_id) throws SQLException;
	
	public List<Oefening> findAll() throws SQLException;
	
	public boolean saveOefening(String oefening) throws SQLException;
	
}
