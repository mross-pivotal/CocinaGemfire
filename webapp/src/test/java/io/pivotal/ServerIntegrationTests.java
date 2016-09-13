package io.pivotal;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import io.mross.Person;
import io.mross.Trade;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerIntegrationTests {

	@Autowired CocinaFunctionExecutor service;

	@Autowired
	ClientCache cache;

	@Before
	public void setup() {
		clearRegion();
		seedPeopleRegion();
	}


	@Test
	public void riskIntegrationTests() {

		Region<String,Person> personRegion = cache.getRegion("People");
		Person seedPerson = new Person("1", "barbara", "showalter", 12);
		personRegion.put(seedPerson.getId(),seedPerson);
		System.out.println("Before" + seedPerson.getRiskFactor());
		service.runCalculateDailyRisk();

		seedPerson = personRegion.get("1");
		Double riskFactor = seedPerson.getRiskFactor();
		assertEquals(Double.valueOf(3.0),riskFactor);

		System.out.println("After" + seedPerson.getRiskFactor());




	}

	@Test
	public void cacheListenerIntegrationTest() {
		String userId = "9";

		Region<String,Person> personRegion = cache.getRegion("People");
		Person seedPerson = new Person(userId, "barbara", "showalter", 12);

		personRegion.put(userId, seedPerson);

		Region<String,Trade> tradeRegion= cache.getRegion("Trades");
		Trade trade = new Trade("1","AAPL",userId,300);
		tradeRegion.put(trade.getId(),trade);

		trade = new Trade("2","AAPL",userId,300);
		tradeRegion.put(trade.getId(),trade);

		trade = new Trade("8","AAPL",userId,300);
		tradeRegion.put(trade.getId(),trade);

		Person person = personRegion.get(trade.getUserId());
		System.out.println(person);
		ArrayList<String> trades = person.getTrades();
		assertTrue(trades.contains("1"));
		assertTrue(trades.contains("2"));
		assertTrue(trades.contains("8"));


	}


	@After
	public  void tearDown() {
		clearRegion();

	}


	private void seedPeopleRegion() {
		Person person;
		Region<String,Person> peopleRegion = cache.getRegion("People");
		for(int x=1; x <= 100; x++) {
			person =  new Person(""+x,"barbara","showalter",x);
			peopleRegion.put(x+"",person);
		}
	}

	private void clearRegion() {
		Execution functionExecution = FunctionService
				.onRegion(cache.getRegion("People"));

		ResultCollector<?,?> results = functionExecution.execute("ClearRegionRemoveAllFunction");
		Object resultWrapper = results.getResult();
		System.out.println(resultWrapper);
	}

}
