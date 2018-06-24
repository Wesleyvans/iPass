package nl.hu.v1c.project.persistence;

public class Workout_oefening {
	private int oefening_id;
	private int workout_id;
	
	public Workout_oefening(int oefening, int workout_id) {
		this.oefening_id = oefening_id;
		this.workout_id = workout_id;
	}
	
	public int getOefening() {
		return oefening_id;
	}
	
	public int getWorkout() {
		return workout_id;
	}
	
}
