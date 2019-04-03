package fr.pizzeria.dao;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class PizzaJdbc implements IPizzaDao {
	private String url;
	private String user;
	private String pass;
	
	public PizzaJdbc() {
		super();
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		url = bundle.getString("jdbc.url");
		user = bundle.getString("jdbc.user");
		pass = bundle.getString("jdbc.pass");
	}

	@Override
	public Pizza[] findAllPizzas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection cn = DriverManager.getConnection(url, user, pass);
			
			Statement st = cn.createStatement();
			
			String query = "select * from pizzas;";
			ResultSet rs = st.executeQuery(query);
			
			rs.last();
			int numberOfPizzas = rs.getRow();
			rs.first();
			Pizza[] pizzas = new Pizza[numberOfPizzas];
			int cpt = 0;
			while (rs.next()){
				String code = rs.getString(2);
				String nom = rs.getString(3);
				float prix = rs.getFloat(4);
				CategoriePizza categoriePizza = CategoriePizza.valueOf(rs.getString(5));
				pizzas[cpt] = new Pizza(code,nom,prix,categoriePizza);
				++cpt;
			}
			return pizzas;
			
		} catch (ClassNotFoundException e) {
			throw new PizzaException("la classe driver n'a pas pu être chargée", e);
		} catch (SQLException e) {
			throw new PizzaException("Problème de connection à la base de donnée", e);
		}
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			Connection cn = DriverManager.getConnection(url, user, pass);
			
			String sql = "INSERT INTO pizzas(code,nom,prix,categorie) "
					+ "VALUES('" + pizza.getCode() + "','" + pizza.getLibelle() + "'," + pizza.getPrix() + ",'" + pizza.getCategoriePizza().toString()+"')";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			throw new PizzaException("la classe driver n'a pas pu être chargée", e);
		} catch (SQLException e) {
			throw new PizzaException("Problème de connection à la base de donnée", e);
		}
		
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, user, pass);
			String sql = "UPDATE pizzas SET code='" + pizza.getCode() + "',nom='" + pizza.getLibelle() + "',prix=" + pizza.getPrix() + ",categorie='" + pizza.getCategoriePizza().toString()+"'"
					+ " WHERE code='" + codePizza+"'";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			throw new PizzaException("la classe driver n'a pas pu être chargée", e);
		} catch (SQLException e) {
			throw new PizzaException("Problème de connection à la base de donnée", e);
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, user, pass);
			String sql = "DELETE FROM pizzas WHERE code = '" + codePizza+"'";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			throw new PizzaException("la classe driver n'a pas pu être chargée", e);
		} catch (SQLException e) {
			throw new PizzaException("Problème de connection à la base de donnée", e);
		}
		
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
