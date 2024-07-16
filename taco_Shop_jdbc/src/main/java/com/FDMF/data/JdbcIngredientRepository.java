package com.FDMF.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.FDMF.model.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient fineOne(String id) {
		return jdbc.queryForObject("SELECT id, name, type FROM Ingredient WHERE id=?", this::mapRowToIngredient, id);
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type")));
	}

}
