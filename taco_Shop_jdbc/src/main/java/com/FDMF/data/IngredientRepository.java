package com.FDMF.data;

import com.FDMF.model.Ingredient;

public interface IngredientRepository {
	Iterable<Ingredient> findAll();
	
	Ingredient fineOne(String id);

}
