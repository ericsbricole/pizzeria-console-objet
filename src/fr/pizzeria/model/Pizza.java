package fr.pizzeria.model;


/**
 * @author Pennecot
 * Cette classe représente une pizza
 */
public class Pizza {

	/**
	 * identifiant de l'instance
	 */
	private int id;
	
	/**
	 * code de la pizza
	 */
	private String code;
	
	/**
	 * libelle de la pizza
	 */
	private String libelle;
	/**
	 * prix de la pizza en euro
	 */
	private double prix;
	/**
	 * compteur du nombre de pizza instanciée
	 */
	private static int cpt = 0;
	
	
	/**
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(String code, String libelle, double prix){
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		cpt++;
		this.id = cpt;
	}
	

	/**
	 * @param id
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(int id, String code, String libelle, double prix){
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
}
