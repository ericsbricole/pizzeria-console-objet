package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.model.IPizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService{

	private IPizzaMemDao dao;	

	public IPizzaMemDao getDao() {
		return dao;
	}

	public void setDao(IPizzaMemDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) {
		System.out.println("Mise à jour d'une pizza");
		System.out.println("Veuillez saisir le code de la pizza à modifier.");
		String codeAModifier = scanner.next();
		System.out.println("Veuillez saisir le nouveau code");
		String code = scanner.next();
		System.out.println("Veuillez saisir le nouveau nom (sans espace");
		String libelle = scanner.next();
		System.out.println("Veuillez saisir le nouveau prix");
		double prix = scanner.nextDouble();
		Pizza updatedPizza = new Pizza(code, libelle, prix);
		dao.updatePizza(codeAModifier, updatedPizza);
	}
	
	
}
