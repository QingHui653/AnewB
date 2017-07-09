package test.core.config;

public abstract class Abstract implements Comparable<Abstract> {
	private String name;
	private String address;
	private int number;

	public abstract double computePay();
	
	@Override
	public int compareTo(Abstract o) {
		return this.name.compareTo(o.name);
	}
}
