package test.a1_8;

import java.util.ArrayList;
import java.util.List;

public class Java8MethodCiteTester {
	/**
	 * 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
final Car car = Car.create( Car::new );
final List< Car > cars = Arrays.asList( car );
静态方法引用：它的语法是Class::static_method，实例如下：
cars.forEach( Car::collide );
特定类的任意对象的方法引用：它的语法是Class::method实例如下：
cars.forEach( Car::repair );
特定对象的方法引用：它的语法是instance::method实例如下：
final Car police = Car.create( Car::new );
cars.forEach( police::follow );
	 * @param args
	 */
	   public static void main(String args[]){
	      List<String> names = new ArrayList<String>();
	        
	      names.add("Google");
	      names.add("Runoob");
	      names.add("Taobao");
	      names.add("Baidu");
	      names.add("Sina");
	        
	      names.forEach(System.out::println);
	   }
	}
