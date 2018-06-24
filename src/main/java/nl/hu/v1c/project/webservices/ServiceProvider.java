package nl.hu.v1c.project.webservices;

public class ServiceProvider {
	private static WorkoutService workoutservice = new WorkoutService();

	public static WorkoutService getWorkoutService() {
		return workoutservice;
	}
}