package nl.hu.v1c.project.webservices;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import nl.hu.v1c.project.persistence.*;

public class WorkoutService {
	private WorkoutPostgresDaoImpl WorkoutPostgresDao = new WorkoutPostgresDaoImpl();
	private OefeningDaoImpl OefeningDaoImpl = new OefeningDaoImpl();
	private CategoriePostgresDaoImpl categoriePostgresDao = new CategoriePostgresDaoImpl();
	private ReactiePostgresDaoImpl reactiePostgresDao = new ReactiePostgresDaoImpl();
	
	
	public List<Workout> getAllWorkouts() throws SQLException {
		return WorkoutPostgresDao.findAll();
	}
//	
//	public Workout getWorkoutByCode(String code) throws SQLException {
//		return WorkoutPostgresDao.findByCode(code);
//	}
//	
//	public boolean Delete(Workout workout) throws SQLException {
//		return WorkoutPostgresDao.Delete(country);
//	}
//	
//	public boolean Update(String code, String name, String capital, 
//			 String region, double surface, int population) throws SQLException {
//		
//		return WorkoutPostgresDao.Update(code,  name, capital, region, surface, population);
//	}
//	
	public int saveWorkout(String titel, String beschrijving, int categorie_id, String gebruikersnaam) throws SQLException, JsonParseException, JsonMappingException, IOException {
		return WorkoutPostgresDao.saveWorkout(titel, beschrijving, categorie_id, gebruikersnaam);
	}
	
	public List<Oefening> findAllOefeningenByCode(int workout_id) throws SQLException {
		return OefeningDaoImpl.findAllByCode(workout_id);
	}
	
	public List<Oefening> findAllOefeningen() throws SQLException {
		return OefeningDaoImpl.findAll();
	}
 	
	public boolean saveOefeningWorkout(int workout_id, int oefening_id) throws SQLException {
		return OefeningDaoImpl.saveOefeningWorkout(workout_id, oefening_id);
	}
	
	public boolean saveOefening(String oefening) throws SQLException {
		return OefeningDaoImpl.saveOefening(oefening);
	}
	
	public boolean saveCategorie(String categorie) throws SQLException {
		return categoriePostgresDao.save(categorie);
	}
	
	public List<Categorie> findAllCategories() throws SQLException {
		return categoriePostgresDao.findAll();
	}
	
	public List<Reactie> findAllReacties(int workout_id) throws SQLException {
		return reactiePostgresDao.findAll(workout_id);
	}
	
	public boolean saveReactie(String gebruikersnaam, String reactie, int workout_id) throws SQLException {
		return reactiePostgresDao.saveReactie(gebruikersnaam, reactie, workout_id);
	}
	
	public boolean saveOefening(int workout_id, int oefening_id) throws SQLException {
		return OefeningDaoImpl.saveOefeningWorkout(workout_id, oefening_id);
	}
	
}
