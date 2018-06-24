package nl.hu.v1c.project.persistence;

public class Reactie {
	private String reactie;
	private String persoon;
	private int workout_id;
	
	public Reactie(String reactie, String persoon, int workout_id) {
		this.reactie = reactie;
		this.persoon = persoon;
		this.workout_id = workout_id;
	}
	
	public int getWorkoutId() {
		return workout_id;
	}
	
	public String getReactie() {
		return reactie;
	}
	
	public String getPersoon() {
		return persoon;
	}
	
}
