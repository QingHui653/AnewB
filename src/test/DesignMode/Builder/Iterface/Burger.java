package test.DesignMode.Builder.Iterface;

import test.DesignMode.Builder.Item;
import test.DesignMode.Builder.Iterface.PackingImpl.Wrapper;

public abstract class Burger implements Item {

	@Override
	public Packing packing() {
		return new Wrapper();
	}

	@Override
	public abstract float price();
}
