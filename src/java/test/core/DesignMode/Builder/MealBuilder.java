package test.core.DesignMode.Builder;

import test.core.DesignMode.Builder.Iterface.BurgerImpl.ChickenBurger;
import test.core.DesignMode.Builder.Iterface.BurgerImpl.VegBurger;
import test.core.DesignMode.Builder.Iterface.ColdDrinkImpl.Coke;
import test.core.DesignMode.Builder.Iterface.ColdDrinkImpl.Pepsi;

public class MealBuilder {

	public Meal prepareVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new VegBurger());
		meal.addItem(new Coke());
		return meal;
	}

	public Meal prepareNonVegMeal() {
		Meal meal = new Meal();
		meal.addItem(new ChickenBurger());
		meal.addItem(new Pepsi());
		return meal;
	}
}
