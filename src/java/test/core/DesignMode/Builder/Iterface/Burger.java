package test.core.DesignMode.Builder.Iterface;

import test.core.DesignMode.Builder.Item;
import test.core.DesignMode.Builder.Iterface.PackingImpl.Wrapper;

public abstract class Burger implements Item {

	@Override
	public Packing packing() {
		return new Wrapper();
	}

	@Override
	public abstract float price();
}
