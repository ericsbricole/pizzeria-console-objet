package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.IPizzaMemDao;

public class SupprimerPizzaService extends MenuService {
	
	IPizzaMemDao dao;

	
	
	public IPizzaMemDao getDao() {
		return dao;
	}

	public void setDao(IPizzaMemDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		System.out.println("Suppression d'une pizza");
		System.out.println("Code de la pizza à supprimer?");
		String codeASuppr = scanner.next();
		if (codeASuppr.length() != 3 || !codeASuppr.equals(codeASuppr.toUpperCase()))
			throw new DeletePizzaException("Le code de la pizza à supprimer n'est pas correcte");
		dao.deletePizza(codeASuppr);
	}
}
