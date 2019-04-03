package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService{

	private IPizzaDao dao;	

	public IPizzaDao getDao() {
		return dao;
	}

	public void setDao(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws UpdatePizzaException {
		System.out.println("Mise à jour d'une pizza");
		System.out.println("Veuillez saisir le code de la pizza à modifier.");
		String codeAModifier = scanner.next();
		System.out.println("Veuillez saisir le nouveau code");
		String code = scanner.next();
		if (code.length() != 3 || !code.equals(code.toUpperCase())){
			throw new UpdatePizzaException("Le nouveau code de la pizza n'est pas correct");
		}
		System.out.println("Veuillez saisir le nouveau nom (sans espace");
		String libelle = scanner.next();
		System.out.println("Veuillez saisir le nouveau prix");
		double prix = scanner.nextDouble();
		System.out.println("Veuillez saisir la nouvelle catégorie.1 = viande, 2 = sans viande, 3 = poisson");
		CategoriePizza categoriePizza = null;
		int choosenCategorie = scanner.nextInt();
		if (choosenCategorie == 1)
			categoriePizza = CategoriePizza.VIANDE;
		if (choosenCategorie == 2)
			categoriePizza = CategoriePizza.SANS_VIANDE;
		if (choosenCategorie == 3)
			categoriePizza = CategoriePizza.POISSON;
		Pizza updatedPizza = new Pizza(code, libelle, prix, categoriePizza);
		dao.updatePizza(codeAModifier, updatedPizza);
	}
	
	
}
