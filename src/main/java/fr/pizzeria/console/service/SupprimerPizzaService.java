package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.PizzaException;

public class SupprimerPizzaService extends MenuService {
	
	IPizzaDao dao;

	
	
	public IPizzaDao getDao() {
		return dao;
	}

	public void setDao(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws PizzaException {
		System.out.println("Suppression d'une pizza");
		System.out.println("Code de la pizza à supprimer?");
		String codeASuppr = scanner.next();
		if (codeASuppr.length() != 3 || !codeASuppr.equals(codeASuppr.toUpperCase()))
			throw new DeletePizzaException("Le code de la pizza à supprimer n'est pas correcte");
		dao.deletePizza(codeASuppr);
	}
}
