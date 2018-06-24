package nl.hu.v1c.project.webservices;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import nl.hu.v1c.project.persistence.*;

@Path("workouts")
public class WorkoutResource {
	@GET
	@Produces("application/json")
	public String getAllWorkouts() throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Workout> allWorkouts = service.getAllWorkouts();
		
		for(Object obj : allWorkouts) {
			Workout workout = (Workout)obj;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("workout_id", workout.getId());
			job.add("titel", workout.getTitel());
			job.add("beschrijving", workout.getBeschrijving());
			job.add("persoon", workout.getPersoon());
			job.add("categorie", workout.getCategorie());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{workout_id}")
	@Produces("application/json")
	public String findAllOefeningenByCode(@PathParam("workout_id") int workout_id) throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Oefening> allOefeningen = service.findAllOefeningenByCode(workout_id);
		
		for(Object obj : allOefeningen) {
			Oefening oefening = (Oefening)obj;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("workout_id", oefening.getWorkout());
			job.add("oefening_id", oefening.getOefeningId());
			job.add("oefening", oefening.getOefening());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("getAllOefeningen")
	@Produces("application/json")
	public String findAllOefeningen() throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Oefening> allOefeningen = service.findAllOefeningen();
		
		for(Object obj : allOefeningen) {
			Oefening oefening = (Oefening)obj;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("oefening_id", oefening.getOefeningId());
			job.add("oefening", oefening.getOefening());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("Categorieen")
	@Produces("application/json")
	public String getAllCategories() throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Categorie> alleCategories = service.findAllCategories();
		
		for(Object obj : alleCategories) {
			Categorie categorie = (Categorie)obj;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("categorie_id", categorie.getId());
			job.add("categorie", categorie.getCategorie());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("insert")
	@POST
	@Produces("application/json")
	@RolesAllowed("user")
	public Response saveWorkout(@Context SecurityContext sc, @FormParam("titel") String titel, @FormParam("beschrijving") String beschrijving,
			@FormParam("categorie_id") int categorie_id) throws SQLException, JsonParseException, JsonMappingException, IOException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		String user = sc.getUserPrincipal().getName();
		int workout = service.saveWorkout(titel, beschrijving, categorie_id, user);
		
		return Response.ok(workout).build();
	}
	
	@Path("insertOefening")
	@POST
	@Produces("application/json")
	@RolesAllowed("admin")
	public Response saveOefening(@FormParam("oefening") String oefening) throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		boolean country = service.saveOefening(oefening);	
		
		return Response.ok(country).build();
	}
	
	@Path("insertCategorie")
	@POST
	@Produces("application/json")
	@RolesAllowed("admin")
	public Response saveCategorie(@FormParam("categorie") String categorie) throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		boolean country = service.saveCategorie(categorie);	
		
		return Response.ok(country).build();
	}
	
	@GET
	@Path("reacties/{workout_id}")
	@Produces("application/json")
	public String getAllReacties(@PathParam("workout_id") int workout_id) throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		List<Reactie> alleReacties = service.findAllReacties(workout_id);
		
		for(Object obj : alleReacties) {
			Reactie reactie = (Reactie)obj;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("reactie", reactie.getReactie());
			job.add("persoon", reactie.getPersoon());
			job.add("workout_id", reactie.getWorkoutId());
			
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@POST
	@Path("insertReactie")
	@Produces("application/json")
	@RolesAllowed({"admin","user"})
	public Response saveReactie(@Context SecurityContext sc, @FormParam("reactie") String reactie, @FormParam("workout_id") int workout_id) throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		String user = sc.getUserPrincipal().getName();
		boolean result = service.saveReactie(user,reactie,workout_id);	
		
		return Response.ok(result).build();
	}
	
	@POST
	@Path("insertOefeningWorkout")
	@Produces("application/json")
	@RolesAllowed("user")
	public Response saveOefeningWorkout(@FormParam("workout_id") int workout_id, @FormParam("oefening_id") int oefening_id) throws SQLException {
		WorkoutService service = ServiceProvider.getWorkoutService();
		boolean result = service.saveOefeningWorkout(workout_id, oefening_id);	
		
		return Response.ok(result).build();
	}
	


}
	

	
