package fr.pizzeria.console.service;

import java.util.Scanner;

import fr.pizzeria.exception.StockageException;

public abstract class MenuService {
	
	public abstract void executeUC(Scanner scanner) throws StockageException;
	
}
