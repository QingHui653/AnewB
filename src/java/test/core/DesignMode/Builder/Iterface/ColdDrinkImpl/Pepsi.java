package test.core.DesignMode.Builder.Iterface.ColdDrinkImpl;

import test.core.DesignMode.Builder.Iterface.ColdDrink;

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
