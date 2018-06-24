package nl.hu.v1c.project.persistence;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface WorkoutDao {
	public int saveWorkout(String titel, String beschrijving, int categorie, String gebruikersnaam) throws SQLException;
	
	public List<Workout> findWorkouts(String query) throws SQLException;
	
	public List<Workout> findAll() throws SQLException;
	
	public Workout findByCode(int code) throws SQLException;

}
