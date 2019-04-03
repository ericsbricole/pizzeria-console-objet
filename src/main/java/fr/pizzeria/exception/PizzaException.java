package fr.pizzeria.exception;

public class PizzaException extends RuntimeException {

	public PizzaException(String msg){
		super(msg);
	}
	
	public PizzaException(String msg, Throwable cause){
		super(msg, cause);
	}
	
}
