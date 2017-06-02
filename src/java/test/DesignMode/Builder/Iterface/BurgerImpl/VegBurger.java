package test.DesignMode.Builder.Iterface.BurgerImpl;

import test.DesignMode.Builder.Iterface.Burger;

public class VegBurger extends Burger {

	@Override
	public String name() {
		return "Veg Burger";
	}

	@Override
	public float price() {
		return 25.0f;
	}

}
