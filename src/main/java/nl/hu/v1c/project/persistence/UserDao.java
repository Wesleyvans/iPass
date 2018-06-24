package nl.hu.v1c.project.persistence;

public interface UserDao {
	public String findRoleForUser(String username, String password);
}
