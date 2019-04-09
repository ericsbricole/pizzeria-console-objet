package fr.pizzeria.exception;

public class DeletePizzaException extends PizzaException {
	
	public DeletePizzaException(String msg){
		super(msg);
	}

	public DeletePizzaException(String msg, Throwable cause) {
		super(msg, cause);
	}

	
}
