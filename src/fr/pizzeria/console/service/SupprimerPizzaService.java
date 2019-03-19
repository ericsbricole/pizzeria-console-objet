package fr.pizzeria.console.service;

import java.util.Scanner;

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
	public void executeUC(Scanner scanner) {
		System.out.println("Suppression d'une pizza");
		String codeASuppr = scanner.next();
		dao.deletePizza(codeASuppr);
	}
	
	

}
