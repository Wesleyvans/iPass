package nl.hu.v1c.project.persistence;

import java.util.ArrayList;

public class Workout {
	private int id;
	private String titel;
	private String beschrijving;
	private String persoon;
	private String categorie;
	private ArrayList<Reactie> allReacties = new ArrayList<Reactie>();
	
	public Workout(int id, String titel, String beschrijving, String persoon, String categorie) {
		this.id = id;
		this.titel = titel;
		this.beschrijving = beschrijving;
		this.persoon = persoon;
		this.categorie = categorie;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitel() {
		return titel;
	}
	
	public String getBeschrijving() {
		return beschrijving;
	}
	
	public String getPersoon() {
		return persoon;
	}
	
	public String getCategorie() {
		return categorie;
	}
}
