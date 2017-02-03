package test.DesignMode.Builder.Iterface.ColdDrinkImpl;

import test.DesignMode.Builder.Iterface.ColdDrink;

public class Coke extends ColdDrink {

	@Override
	public String name() {
		return "Coke";
	}

	@Override
	public float price() {
		return 30.0f;
	}

}
