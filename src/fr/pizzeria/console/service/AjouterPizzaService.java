package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.IPizzaMemDao;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class AjouterPizzaService extends MenuService {
	
	private IPizzaMemDao dao;

	public IPizzaMemDao getDao() {
		return dao;
	}
	
	public void setDao(IPizzaMemDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws SavePizzaException {
		System.out.println("Ajout d'une pizza");
		System.out.println("Veuillez saisir le code");
		String code = scanner.next();
		if (code.length() != 3)
			throw new SavePizzaException("Le code de la pizza doit avoir 3 charactères");
		System.out.println("Veuillez saisir le nom (sans espace");
		String libelle = scanner.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scanner.nextDouble();
		System.out.println("Quelle est la catégorie de cette pizza? 1 = viande, 2 = sans viande, 3 = poisson");
		CategoriePizza categoriePizza = null;
		int choosenCategorie = scanner.nextInt();
		if (choosenCategorie == 1)
			categoriePizza = CategoriePizza.VIANDE;
		if (choosenCategorie == 2)
			categoriePizza = CategoriePizza.SANS_VIANDE;
		if (choosenCategorie == 3)
			categoriePizza = CategoriePizza.POISSON;
		Pizza nouvellePizza = new Pizza(code, libelle, prix, categoriePizza);
		dao.saveNewPizza(nouvellePizza);
	}

}
