package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Pennecot
 * Cette classe représente une pizza
 */
@Entity
@Table(name="PIZZAS")
public class Pizza {

	/**
	 * identifiant de l'instance
	 */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**
	 * code de la pizza
	 */
	@Column(name="CODE")
	private String code;
	
	/**
	 * libelle de la pizza
	 */
	@Column(name="LIBELLE")
	private String libelle;
	/**
	 * prix de la pizza en euro
	 */
	@Column(name="PRIX")
	private double prix;
	/**
	 * compteur du nombre de pizza instanciée
	 */
	@Transient
	public static int cpt = 0;
	
	@Enumerated(EnumType.STRING)
	private CategoriePizza categoriePizza;
	
	public Pizza() { }
	
	/**
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categoriePizza){
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		cpt++;
		this.id = cpt;
		this.categoriePizza = categoriePizza;
	}
	

	/**
	 * @param id
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza categoriePizza){
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.id = id;
		this.categoriePizza = categoriePizza;
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

	public CategoriePizza getCategoriePizza() {
		return categoriePizza;
	}


	public void setCategoriePizza(CategoriePizza categoriePizza) {
		this.categoriePizza = categoriePizza;
	}

	

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", code=" + code + ", libelle=" + libelle + ", prix=" + prix + "categorie = " + categoriePizza + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriePizza == null) ? 0 : categoriePizza.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + id;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (categoriePizza != other.categoriePizza)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id != other.id)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}
	
}
