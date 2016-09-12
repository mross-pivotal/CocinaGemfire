package io.pivotal;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import io.mross.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CocinaApplicationTests {

	@Autowired CocinaFunctionExecutor service;

	@Autowired
	ClientCache cache;

	@Test
	public void averageIntegrationTests() {

		seedRegion();

//		test();
//
		int calculatedAverage = service.runCalculateAverage();
//
		System.out.println("Final result is "  +calculatedAverage);
//		assertEquals(5050,calculatedAverage);


			clearRegion();

	}

	public void test() {
		int avg = 0;
		for(int x=1; x <= 100; x++){
			 avg += x;
		}
		System.out.println("###" + avg);
		avg = avg /100;
		System.out.println(avg);

	}

	private void seedRegion() {
		Person person;
		int avg = 0;

		Region<String,Person> peopleRegion = cache.getRegion("People");
		for(int x=1; x <= 100; x++) {
			person =  new Person(""+x,"barbara","showalter","N/A",x);
			avg += x;
			System.out.println(x);
			peopleRegion.put(x+"",person);
		}
		System.out.println("###" + avg);
		avg = avg /100;
		System.out.println(avg);
	}

	private void clearRegion() {
		Execution functionExecution = FunctionService
				.onRegion(cache.getRegion("People"));

		ResultCollector<?,?> results = functionExecution.execute("ClearRegionRemoveAllFunction");
		Object resultWrapper = results.getResult();
		System.out.println(resultWrapper);
	}

}
