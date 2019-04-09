package fr.pizzeria.exception;

public class SavePizzaException extends PizzaException {
	
	public SavePizzaException(String msg){
		super(msg);
	}

	public SavePizzaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
