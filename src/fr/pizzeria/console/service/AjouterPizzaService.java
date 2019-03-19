package fr.pizzeria.console.service;

import java.util.Scanner;

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
	public void executeUC(Scanner scanner) {
		System.out.println("Ajout d'une pizza");
		System.out.println("Veuillez saisir le code");
		String code = scanner.next();
		System.out.println("Veuillez saisir le nom (sans espace");
		String libelle = scanner.next();
		System.out.println("Veuillez saisir le prix");
		double prix = scanner.nextDouble();
		Pizza nouvellePizza = new Pizza(code, libelle, prix);
		dao.saveNewPizza(nouvellePizza);
	}

}
