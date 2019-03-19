package fr.pizzeria.console;

import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import fr.pizzeria.console.service.AjouterPizzaService;
import fr.pizzeria.console.service.ListerPizzasService;
import fr.pizzeria.console.service.MenuService;
import fr.pizzeria.console.service.ModifierPizzaService;
import fr.pizzeria.console.service.SupprimerPizzaService;
import fr.pizzeria.model.IPizzaMemDao;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class PizzeriaAdminConsole {

	public static void main(String[] args) {

		IPizzaMemDao dao = new PizzaMemDao();
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
		while (choice != 99){
			System.out.println("***Pizzeria administration****");
			System.out.println("1. Lister les pizza");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre � jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			choice = sc.nextInt();
			
			if (choice == 1){
				listerService.executeUC(sc);
			}
			
			if (choice == 2){
				ajouterService.executeUC(sc);
			}
			
			if (choice == 3){
				modifierService.executeUC(sc);				
			}
			
			if (choice == 4){
				supprimerService.executeUC(sc);
				
			}
		}
		
		System.out.println("Au revoir + \u2639!");
	}

}
