package fr.pizzeria.model;

public class PizzaMemDao implements IPizzaMemDao{
	
	private Pizza[] pizzas = {
			new Pizza("PEP", "Pepperroni", 12.50),
			new Pizza("MAR", "Margherita", 14.00),
			new Pizza("REIN", "La Reine", 11.50),
			new Pizza("FRO", "La 4 fromages", 12.00),
			new Pizza("CAN", "La cannibale", 12.50),
			new Pizza("SAV", "La Savoyarde", 13.00),
			new Pizza("ORI", "L'orientale", 13.50),
			new Pizza("IND", "L'indienne", 14.00)
	};
		
	public Pizza[] getPizzas() {
		return pizzas;
	}

	public void setPizzas(Pizza[] pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public Pizza[] findAllPizzas() {
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		int numberOfPizzas = pizzas.length + 1;
		Pizza[] tmp = pizzas.clone();
		pizzas = new Pizza[numberOfPizzas];
		for (int i = 0; i < tmp.length; i++){
			pizzas[i] = tmp[i];
		}
		pizzas[pizzas.length -1] = pizza;	
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		for (int i = 0; i < pizzas.length; i++){
			if (pizzas[i].getCode().equals(codePizza)){
				pizzas[i] = pizza;
			}
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		Pizza[] tmp = pizzas.clone();
		pizzas = new Pizza[tmp.length - 1];
		int indiceOfPizzas = 0;
		for (int i = 0; i < tmp.length; i++){
			if (!tmp[i].getCode().equals(codePizza)){
				pizzas[indiceOfPizzas] = tmp[i];
				indiceOfPizzas++;
			}
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		Pizza pizza = null;
		for (int i = 0; i < pizzas.length; i++){
			if (pizzas[i].getCode().equals(codePizza)){
				pizza = pizzas[i];
			}
		}
		return pizza;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		for (int i = 0; i < pizzas.length; i++){
			if (pizzas[i].getCode().equals(codePizza)){
				return true;
			}
		}
		return false;
	}

}
