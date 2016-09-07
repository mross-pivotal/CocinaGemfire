package io.pivotal;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CocinaApplicationTests {

	@Autowired io.pivotal.FunctionService service;

	@Autowired
	ClientCache cache;

	@Test
	public void contextLoads() {

//		ApplicationContext context = new ClassPathXmlApplicationContext("config/sdg-context.xml");


		Execution functionExecution = FunctionService.onRegion(cache.getRegion("test"));

		ResultCollector<?,?> results = functionExecution.execute("GemfireFunction");

		List<?> list = (List<?>)results.getResult();


		System.out.println(list);

		System.out.println(service.sayHi());


	}

}
