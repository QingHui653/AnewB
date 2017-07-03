package test.core.java1_8;

public class Java8InterfaceTester {
	/**
	 * 默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
我们只需在方法名前面加个default关键字即可实现默认方法。
	 * @param args
	 */
	   public static void main(String args[]){
	      Vehicle vehicle = new Car();
	      vehicle.print();
	   }
	}
	 
	interface Vehicle {
	   default void print(){
	      System.out.println("我是一辆车!");
	   }
	    
	   static void blowHorn(){
	      System.out.println("按喇叭!!!");
	   }
	}
	 
	interface FourWheeler {
	   default void print(){
	      System.out.println("我是一辆四轮车!");
	   }
	}
	 
	class Car implements Vehicle, FourWheeler {
	   public void print(){
	      Vehicle.super.print();
	      FourWheeler.super.print();
	      Vehicle.blowHorn();
	      System.out.println("我是一辆汽车!");
	   }
	}
