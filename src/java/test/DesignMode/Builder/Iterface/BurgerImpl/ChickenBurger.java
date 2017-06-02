package test.DesignMode.Builder.Iterface.BurgerImpl;

import test.DesignMode.Builder.Iterface.Burger;

public class ChickenBurger extends Burger {
	
	@Override
	public String name() {
		return "ChickenBurger";
	}

	@Override
	public float price() {
		return 50.5f;
	}
}
