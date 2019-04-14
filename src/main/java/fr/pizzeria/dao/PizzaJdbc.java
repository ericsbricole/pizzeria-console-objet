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
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaJdbc implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaJdbc.class);

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

	private interface functionSQL<X,Y>{
		Y apply(X x) throws SQLException;
	}

	public <O> O executerSQL(functionSQL<Connection, O> fn){
		try(Connection cn = DriverManager.getConnection(url, user, pass)){
			return fn.apply(cn);			
		} catch (SQLException e) {
			throw new PizzaException("Problème lors de l'exécution de la requète ", e);
		}
	}

	@Override
	public Pizza[] findAllPizzas() {

		return executerSQL( cn ->{
			String sql = "select * from PIZZAS;";
			try(Statement st = cn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					) {
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
			}
		});
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		executerSQL( cn -> {
			String sql = "INSERT INTO PIZZAS(code,nom,prix,categorie) "
					+ "VALUES('" + pizza.getCode() + "','" + pizza.getLibelle() + "'," + pizza.getPrix() + ",'" + pizza.getCategoriePizza().toString()+"')";
			try (PreparedStatement pst = cn.prepareStatement(sql)) {
				pst.executeUpdate(sql);
				return null;
			}
		});
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		executerSQL( cn -> {
			String sql = "UPDATE PIZZAS SET code='" + pizza.getCode() + "',nom='" + pizza.getLibelle() + "',prix=" + pizza.getPrix() + ",categorie='"
					+ pizza.getCategoriePizza().toString()+"' WHERE code='" + codePizza+"'";
			try (Statement st = cn.createStatement()){
				st.executeUpdate(sql);
				return null;
			}
		});
	}

	@Override
	public void deletePizza(String codePizza) {
		executerSQL( cn -> {
			String sql = "DELETE FROM PIZZAS WHERE code = '" + codePizza+"'";
			try(PreparedStatement pst = cn.prepareStatement(sql)){
				pst.executeUpdate(sql);
				return null;
			}
		});
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		return executerSQL( cn -> {
			String sql = "SELECT * FROM PIZZAS WHERE p.code = ?";
			Pizza pizza = null;
			
			try(PreparedStatement pst = cn.prepareStatement(sql)){
				pst.setString(1, codePizza);
				ResultSet rs = pst.executeQuery();
				if (rs.getFetchSize() != 1)
					return null;
				String code = rs.getString(2);
				String libelle = rs.getString(3);
				float prix = rs.getFloat(4);
				CategoriePizza categoriePizza = CategoriePizza.valueOf(rs.getString(5));
				Pizza p = new Pizza(code, libelle, prix, categoriePizza);
				return p;
			}
		});
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		return executerSQL( cn -> {
			String sql = "SELECT * FROM PIZZAS WHERE p.code = ?";
			Pizza pizza = null;
			
			try(PreparedStatement pst = cn.prepareStatement(sql)){
				pst.setString(1, codePizza);
				ResultSet rs = pst.executeQuery();
				boolean exists = rs.getFetchSize() == 1;
				return exists;
			}
		});
	}


}
