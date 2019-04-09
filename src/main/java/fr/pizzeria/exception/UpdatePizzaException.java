package fr.pizzeria.exception;

public class UpdatePizzaException extends PizzaException {

	public UpdatePizzaException(String msg){
		super(msg);
	}

	public UpdatePizzaException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	
}
