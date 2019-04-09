package fr.pizzeria.service;


import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaServiceTest {

	private IPizzaDao dao;
	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	@Before
	public void setUp(){
		dao = Mockito.mock(IPizzaDao.class);
//		systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	}
	
	@Test
	public void executeUCTest(){
		systemInMock.provideLines("ORI", "NEW", "newPizza","11","2");
		ModifierPizzaService service = new ModifierPizzaService(dao);
		service.executeUC(new Scanner(System.in));
		
		Mockito.verify(dao).updatePizza("ORI", new Pizza(1, "NEW","newPizza",11.,CategoriePizza.SANS_VIANDE));
	}

}
