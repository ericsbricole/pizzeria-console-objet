package fr.pizzeria.service;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.TestName;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaServiceTest {
	
	private IPizzaDao dao;
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaServiceTest.class);
	@Rule
	public TestName testName;
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUp(){
		LOG.info("Etant donné une instance de dao mockée");
		dao = Mockito.mock(IPizzaDao.class);
		testName = new TestName();
	}

	@Test
	public void executeUCTest() {
		LOG.trace("éxécution de la méthode {}", testName.getMethodName());
		// définir comportement du mock
		Pizza pizza = new Pizza("VEG", "vegetale", 12.0, CategoriePizza.SANS_VIANDE);
		
		LOG.info("Lorsque saisie utilisateur d'une nouvelle pizza");
		systemInMock.provideLines("VEG", "vegetale", "12.00", "2");

		AjouterPizzaService ajouterPizzasService = new AjouterPizzaService(dao);
		ajouterPizzasService.executeUC(new Scanner(System.in));

		// vérifier que la méthode saveNewPizza du mock a été invoquée
		Mockito.verify(dao).saveNewPizza(pizza);
	}

}
