package io.mross;

import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by mross on 9/9/16.
 */


public class PersonServiceTests {


  @Test
  public void testAverage() {

    ArrayList<Person> people = new ArrayList<Person>();
    PersonCalculatorService service = new PersonCalculatorService();



    Person p1 = new Person("1","Alice","Glass",4);
    Person p2 = new Person("2","Bob","Glass",8);
    Person p3 = new Person("3","STeve","Glass",12);

    people.add(p1);
    people.add(p2);
    people.add(p3);



    int average = service.calculateAverage(people);
    assertEquals(8,average);


  }

  @Test
  public void testDivideByZero() {

    ArrayList<Person> people = new ArrayList<Person>();
    PersonCalculatorService service = new PersonCalculatorService();

    int average = service.calculateAverage(people);
    assertEquals(0,average);
  }
}
