package nl.hu.v1c.project.persistence;


import java.sql.SQLException;
import java.util.List;

public interface ReactieDao {
	public boolean saveReactie(String gebruikersnaam, String reactie, int workout_id) throws SQLException;
	
	public List<Reactie> findReacties(String query) throws SQLException;
	
	public List<Reactie> findAll(int workout_id) throws SQLException;

	
}
