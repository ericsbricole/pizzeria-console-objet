package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.exception.PizzaException;

public abstract class MenuService {
	
	public abstract void executeUC(Scanner scanner) throws PizzaException;
	
}
