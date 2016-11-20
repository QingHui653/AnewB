package newb.c.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  ombok注解：
   @Data：注解在类上；提供类所有属性的setting和getting方法,此外还提供了equals、canEqual、hashCode、toString方法；
   @Setter：注解在属性上，为属性提供了setting方法；
   @Getter：注解在属性上，为属性提供了getting方法；
   @Log4j：注解在类上，为类提供一个属性名为log的log4j日志对象(需要log4j的jar包)；
   @NoArgsConstructor：注解在类上，为类提供了一个无参的构造方法；
   @AllArgsConstructor：注解在类上，为类提供了一个全参的构造方法；
   @EqualsAndHashCode：注解在类上，为类提供equals()方法和hashCode()方法；
   @ToString：注解在类上，为类提供toString()方法；
   @Cleanup : 关闭流 -- 
   @Synchronized：对象同步
   @SneakyThrows：抛出异常
 * @author woshizbh
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepList {
	private String result;
	private Long count;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
