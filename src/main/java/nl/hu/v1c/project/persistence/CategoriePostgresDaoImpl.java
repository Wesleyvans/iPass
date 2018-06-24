package nl.hu.v1c.project.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriePostgresDaoImpl extends PostgresBaseDao implements CategorieDao {

	@Override
	public boolean save(String categorie) throws SQLException {
		boolean result = false;
		try (Connection con = super.getConnection()) {
			String query = "INSERT INTO \"Categorie\" VALUES(?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, categorie);
			pstmt.executeUpdate();
			result = true;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Categorie> findCategorieen(String query) throws SQLException {
		List<Categorie> results = new ArrayList<Categorie>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int categorie_id = dbResultSet.getInt("categorie_id");
				String categorie = dbResultSet.getString("categorie");
				
			results.add(new Categorie(categorie_id, categorie));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}

	@Override
	public List<Categorie> findAll() throws SQLException {
		return findCategorieen("SELECT categorie_id, categorie FROM \"Categorie\"");
	}



}
