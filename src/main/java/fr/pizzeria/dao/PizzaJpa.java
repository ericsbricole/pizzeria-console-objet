package fr.pizzeria.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

public class PizzaJpa implements IPizzaDao{
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public PizzaJpa() {
		emf = Persistence.createEntityManagerFactory("pizzeria-console-objet");
		em = emf.createEntityManager();
	}

	@Override
	public Pizza[] findAllPizzas() {
		String query = "SELECT * FROM Pizza";
		TypedQuery<Pizza> tq = em.createQuery(query, Pizza.class);
		Pizza[] pizzas =  (Pizza[]) tq.getResultList().toArray();
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePizza(String codePizza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

}
