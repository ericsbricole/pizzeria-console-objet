package fr.pizzeria.service;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MenuService.class);
	private IPizzaDao dao;

	public AjouterPizzaService() {	}
	
	public AjouterPizzaService(IPizzaDao dao) {
		this.dao = dao;
	}

	public IPizzaDao getDao() {
		return dao;
	}
	
	public void setDao(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner){
		System.out.println("Ajout d'une pizza");
		System.out.println("Veuillez saisir le code");
		String code = scanner.next();
		LOG.info("Code saisi par l'utilisateur: {} ", code );
		if (code.length() != 3){
			LOG.error("Le code saisi par l'utilisateur ne fait pas 3  charactères");
			throw new SavePizzaException("Le code de la pizza doit avoir 3 charactères");			
		}
		System.out.println("Veuillez saisir le nom (sans espace)");
		String libelle = scanner.next();
		LOG.info("libelle saisi: {} ", libelle );
		System.out.println("Veuillez saisir le prix");
		double prix = Double.parseDouble(scanner.next());
		LOG.info("prix saisi: {} ", prix );
		System.out.println("Quelle est la catégorie de cette pizza? 1 = viande, 2 = sans viande, 3 = poisson");
		CategoriePizza categoriePizza = null;
		int choosenCategorie = Integer.parseInt(scanner.next());
		LOG.info("Categorie saisie: {} ", choosenCategorie );
		if (choosenCategorie == 1)
			categoriePizza = CategoriePizza.VIANDE;
		else if (choosenCategorie == 2)
			categoriePizza = CategoriePizza.SANS_VIANDE;
		else if (choosenCategorie == 3)
			categoriePizza = CategoriePizza.POISSON;
		Pizza nouvellePizza = new Pizza(1, code, libelle, prix, categoriePizza);
		dao.saveNewPizza(nouvellePizza);
	}

}
