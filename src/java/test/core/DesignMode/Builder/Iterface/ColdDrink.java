package test.core.DesignMode.Builder.Iterface;

import test.core.DesignMode.Builder.Item;
import test.core.DesignMode.Builder.Iterface.PackingImpl.Bottle;

public abstract class ColdDrink implements Item {

	@Override
	public Packing packing() {
		return new Bottle();
	}

	@Override
	public abstract float price();

}
