package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import fr.pizzeria.console.service.AjouterPizzaService;
import fr.pizzeria.console.service.ListerPizzasService;
import fr.pizzeria.console.service.MenuService;
import fr.pizzeria.console.service.ModifierPizzaService;
import fr.pizzeria.console.service.SupprimerPizzaService;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJdbc;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsole {

	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaAdminConsole.class);

	public static void main(String[] args) {

		IPizzaDao dao = new PizzaJdbc();
		ListerPizzasService listerService = new ListerPizzasService();
		listerService.setDao(dao);
		AjouterPizzaService ajouterService = new AjouterPizzaService();
		ajouterService.setDao(dao);
		ModifierPizzaService modifierService = new ModifierPizzaService();
		modifierService.setDao(dao);
		SupprimerPizzaService supprimerService = new SupprimerPizzaService();
		supprimerService.setDao(dao);

		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice != 99) {
			System.out.println("***Pizzeria administration****");
			System.out.println("1. Lister les pizza");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			choice = sc.nextInt();

			if (choice == 1) {
				listerService.executeUC(sc);
			}

			if (choice == 2) {
				try {
					ajouterService.executeUC(sc);
				} catch (SavePizzaException e) {
					LOG.error(e.getMessage(), e.getStackTrace());
					System.err.println(e.getMessage());
				}
			}

			if (choice == 3) {
				try {
					modifierService.executeUC(sc);
				} catch (UpdatePizzaException e) {
					LOG.error("erreur pendant ajout de la pizza ", e);
					System.err.println(e.getMessage());
				}
			}

			if (choice == 4) {
				try {
					supprimerService.executeUC(sc);
				} catch (PizzaException e) {
					LOG.error(e.getMessage(), e.getStackTrace());
					System.err.println(e.getMessage());
				}

			}
		}
		System.out.println("Au revoir + \u2639!");
	}

}
