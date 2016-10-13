package newb.c.api;

import newb.c.service.ResultService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class resultPipe implements Pipeline {
	
	@Autowired
	private ResultService resultService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void process(ResultItems resultItems, Task task) {
		System.out.println(" -----HHHHHHHHHHHHH----------- "+resultItems.get("result"));
	}

}
