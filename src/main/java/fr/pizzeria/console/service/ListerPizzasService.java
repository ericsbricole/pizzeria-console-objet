package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaMemDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService{

	private IPizzaMemDao dao;
	
	public IPizzaMemDao getDao() {
		return dao;
	}

	public void setDao(IPizzaMemDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) {
		System.out.println("Liste des pizza");
		Pizza[] pizzas = dao.findAllPizzas();
		for (int i = 0; i < pizzas.length; i++){
			Pizza pizza = pizzas[i];
			if (pizza != null)
			System.out.println(pizza.getCode() + "->" + pizza.getLibelle() + "(" + pizza.getPrix() + ") Catégorie: " + pizza.getCategoriePizza());
		}
		System.out.println("");
		
	}

}
