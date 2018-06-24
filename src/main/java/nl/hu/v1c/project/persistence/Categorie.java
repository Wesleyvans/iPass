package nl.hu.v1c.project.persistence;

public class Categorie {
	private int categorie_id;
	private String categorie;
	
	public Categorie(int categorie_id, String categorie) {
		this.categorie_id = categorie_id;
		this.categorie = categorie;
	}
	
	public int getId() {
		return categorie_id;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
}
