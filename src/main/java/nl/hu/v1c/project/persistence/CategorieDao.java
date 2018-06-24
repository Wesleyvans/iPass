package nl.hu.v1c.project.persistence;


import java.sql.SQLException;
import java.util.List;

public interface CategorieDao {
	public boolean save(String categorie_id) throws SQLException;
	
	public List<Categorie> findCategorieen(String query) throws SQLException;
	
	public List<Categorie> findAll() throws SQLException;

}
