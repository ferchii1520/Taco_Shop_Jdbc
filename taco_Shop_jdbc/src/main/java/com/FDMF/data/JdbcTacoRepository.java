package com.FDMF.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.FDMF.model.Ingredient;
import com.FDMF.model.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public Taco save(Taco design) {
		
		design.setCreateAt(new Date());
		
		long tacoId = saveTacoInfo(design);
		design.setId(tacoId);
		
		for(Ingredient ingredient: design.getIngredients()) {
			saveIngredientsToTaco(ingredient, tacoId);
		}
		return design;
	}

	private long saveTacoInfo(Taco design) {
		System.out.println("Fecha de creación del taco: " + design.getCreateAt().getTime()); // Impresión de la fecha
		
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"INSERT INTO Taco (name, createdAt) VALUES(?, ?)", 
				Types.VARCHAR, 
				Types.TIMESTAMP
				);
		pscf.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
				Arrays.asList(design.getName(), new Timestamp(design.getCreateAt().getTime()))
				);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbc.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		
		return key.longValue();
	}
	
	private void saveIngredientsToTaco(Ingredient ingredient, long tacoId) {
		jdbc.update(
				"INSERT INTO Taco_Ingredients(taco_id, ingredient_id) VALUES(?, ?)", 
				tacoId, 
				ingredient.getId()
				);
		
	}

}
