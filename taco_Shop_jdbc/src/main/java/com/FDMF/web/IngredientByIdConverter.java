package com.FDMF.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.FDMF.data.IngredientRepository;
import com.FDMF.model.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Override
	public Ingredient convert(String id) {
		return ingredientRepo.fineOne(id);
	}

}
