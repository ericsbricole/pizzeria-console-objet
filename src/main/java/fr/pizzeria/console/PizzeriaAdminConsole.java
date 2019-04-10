package fr.pizzeria.console;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJdbc;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.ListerPizzasService;
import fr.pizzeria.service.ModifierPizzaService;
import fr.pizzeria.service.SupprimerPizzaService;

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
				} catch (PizzaException e) {
					LOG.error("erreur pendant l'ajout de la pizza ", e);
					System.err.println(e.getMessage());
				}
			}

			if (choice == 3) {
				try {
					modifierService.executeUC(sc);
				} catch (PizzaException e) {
					LOG.error("erreur pendant la modification de la pizza ", e);
					System.err.println(e.getMessage());
				}
			}

			if (choice == 4) {
				try {
					supprimerService.executeUC(sc);
				} catch (PizzaException e) {
					LOG.error("erreur pendant la suppression de la pizza ", e);
					System.err.println(e.getMessage());
				}

			}
		}
		System.out.println("Au revoir + \u2639!");
	}

}
