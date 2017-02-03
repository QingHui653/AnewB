package test.DesignMode.Builder.Iterface;

import test.DesignMode.Builder.Item;
import test.DesignMode.Builder.Iterface.PackingImpl.Bottle;

public abstract class ColdDrink implements Item {

	@Override
	public Packing packing() {
		return new Bottle();
	}

	@Override
	public abstract float price();

}
