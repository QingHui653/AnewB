package test.DesignMode.Builder.Iterface.ColdDrinkImpl;

import test.DesignMode.Builder.Iterface.ColdDrink;

public class Pepsi extends ColdDrink {
	@Override
	public String name() {
		return "Pepsi";
	}

	@Override
	public float price() {
		return 35.0f;
	}
}
