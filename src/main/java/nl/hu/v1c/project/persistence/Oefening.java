package nl.hu.v1c.project.persistence;

public class Oefening {
	private int workout_id;
	private int oefening_id;
	private String oefening;
	
	public Oefening(int workout_id, int oefening_id, String oefening) {
		this.workout_id = workout_id;
		this.oefening = oefening;
	}
	
	public Oefening(int oefening_id, String oefening) {
		this.oefening_id = oefening_id;
		this.oefening = oefening;
	}
	
	public int getWorkout() {
		return workout_id;
	}
	
	public int getOefeningId() {
		return oefening_id;
	}
	
	public String getOefening() {
		return oefening;
	}
	
}
