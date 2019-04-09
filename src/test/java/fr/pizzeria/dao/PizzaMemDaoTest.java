package fr.pizzeria.dao;

import java.util.List;
import java.util.logging.LogManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaMemDao.class);
	private IPizzaDao dao;
	@Rule public TestName testName = new TestName();
	@Rule public ExpectedException exE;
//	@Rule public moc
	
	@Before
	public void setUp(){
		Pizza.cpt = 0;
		LOG.info("Etant donné une instance de PizzaMemDao");
		dao = new PizzaMemDao();
	}
	
	@Test
	public void testFindAllPizza(){
		LOG.trace("Exécution de la méthode {}", testName.getMethodName());
		LOG.info("Lorsque récupération de toutes les pizzas");
		LOG.info("Alors 8 pizzas récupérées");
		Pizza[] actuals = dao.findAllPizzas();
		Assert.assertEquals(actuals.length, 8);
	}
	
	@Test
	public void testSavePizza(){
		/* SOUVENT:
		 * Etant donné...
		 * Lorsque...
		 * Alors...
		 */
		LOG.trace("Appelle de la méthode {}", testName.getMethodName());
		LOG.info("Lorsque ajout d'une nouvelle pizza");
		LOG.info("Alors nouvelle pizza ajoutée au tableau");
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
	
	@Test
	public void testDeletePizza(){
		LOG.trace("Appelle de la méthode {}", testName.getMethodName());
		LOG.info("Lorsque suppression d'une pizza");
		LOG.info("Alors pizza retirée du tableau");
		Pizza[] expecteds = {
				new Pizza(1,"PEP", "Pepperroni", 12.50, CategoriePizza.SANS_VIANDE),
				new Pizza(2,"MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE),
				new Pizza(3,"REIN", "La Reine", 11.50, CategoriePizza.POISSON),
				new Pizza(4,"FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE),
				new Pizza(6,"SAV", "La Savoyarde", 13.00, CategoriePizza.VIANDE),
				new Pizza(7,"ORI", "L'orientale", 13.50, CategoriePizza.VIANDE),
				new Pizza(8,"IND", "L'indienne", 14.00, CategoriePizza.VIANDE)
		};
		dao.deletePizza("CAN");
		Pizza[] actuals = dao.findAllPizzas();
		
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void testFindPizzaByCode(){
		LOG.trace("Appelle de la méthode {}", testName.getMethodName());
		LOG.info("Lorsque recherche d'une pizza de code \"FRO\"");
		LOG.info("Alors pizza La 4 fromages est récupérée");
		Pizza expected = new Pizza(4, "FRO", "La 4 fromages", 12, CategoriePizza.SANS_VIANDE);
		Pizza  actual = dao.findPizzaByCode("FRO");
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPizzaExistsWhenPizzaExists(){
		LOG.trace("Appelle de la méthode {}", testName.getMethodName());
		LOG.info("Lorsque test l'existence d'une pizza de code \"FRO\"");
		LOG.info("Alors test renvoie true");
		boolean expected = true;
		boolean  actual = dao.pizzaExists("FRO");
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPizzaExistsWhenPizzaDoesNotExists(){
		LOG.trace("Appelle de la méthode {}", testName.getMethodName());
		LOG.info("Lorsque test l'existence d'une pizza de code \"FIC\"");
		LOG.info("Alors test renvoie false");
		boolean expected = false;
		boolean  actual = dao.pizzaExists("FIC");
		
		Assert.assertEquals(expected, actual);
	}

}
