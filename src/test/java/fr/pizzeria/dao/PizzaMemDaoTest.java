package fr.pizzeria.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {

	
	IPizzaDao dao;
	
	@Before
	public void setUp(){
		dao = new PizzaMemDao();
		Pizza.cpt = 0;
	}
	
	@Test
	public void testSavePizzaVegetale(){
		Pizza p = new Pizza("VEG", "végétale", 7, CategoriePizza.SANS_VIANDE);
		dao.saveNewPizza(p);
		Pizza[] actuals = dao.findAllPizzas();
		
		Pizza[] expecteds = {
				new Pizza(1,"PEP", "Pepperroni", 12.50, CategoriePizza.SANS_VIANDE),
				new Pizza(2,"MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE),
				new Pizza(3,"REIN", "La Reine", 11.50, CategoriePizza.POISSON),
				new Pizza(4,"FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE),
				new Pizza(5,"CAN", "La cannibale", 12.50, CategoriePizza.VIANDE),
				new Pizza(6,"SAV", "La Savoyarde", 13.00, CategoriePizza.VIANDE),
				new Pizza(7,"ORI", "L'orientale", 13.50, CategoriePizza.VIANDE),
				new Pizza(8,"IND", "L'indienne", 14.00, CategoriePizza.VIANDE),
				new Pizza(9,"VEG", "végétale", 7.00, CategoriePizza.SANS_VIANDE)
		};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
//	@Test
	public void testDeletePizzaCannibale(){
		dao.deletePizza("CAN");
		Pizza[] actuals = dao.findAllPizzas();
		
		Pizza[] expecteds = {
				new Pizza(1,"PEP", "Pepperroni", 12.50, CategoriePizza.SANS_VIANDE),
				new Pizza(2,"MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE),
				new Pizza(3,"REIN", "La Reine", 11.50, CategoriePizza.POISSON),
				new Pizza(4,"FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE),
				new Pizza(7,"SAV", "La Savoyarde", 13.00, CategoriePizza.VIANDE),
				new Pizza(8,"ORI", "L'orientale", 13.50, CategoriePizza.VIANDE),
				new Pizza(9,"IND", "L'indienne", 14.00, CategoriePizza.VIANDE)
		};
		Assert.assertArrayEquals(expecteds, actuals);
		
	}

}
