package test.DesignMode.Builder;

import test.DesignMode.Builder.Iterface.BurgerImpl.ChickenBurger;
import test.DesignMode.Builder.Iterface.BurgerImpl.VegBurger;
import test.DesignMode.Builder.Iterface.ColdDrinkImpl.Coke;
import test.DesignMode.Builder.Iterface.ColdDrinkImpl.Pepsi;

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
