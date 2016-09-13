package io.mross;

import com.gemstone.gemfire.cache.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by mross on 9/12/16.
 */

public class PersonTests {


  @Test
  public void testAverage() {
    String userId = "1";


    Person p1 = new Person(userId,"Alice","Glass",4);

    ArrayList<String> trades = new ArrayList<>();
    assertEquals(trades,p1.getTrades());
    Trade t = new Trade("123","AAPL","1",320);
    p1.addTrade(t.getId());
    trades.add(t.getId());
    assertEquals(trades,p1.getTrades());

  }


  @Test
  public void testCalculateRisk() {

    String userId = "3";
    Person p1 = new Person(userId,"Alice","Glass",4);

    p1.calculateRiskFactor(.5);
    Double riskFactor =p1.getRiskFactor();
    assertEquals(Double.valueOf(1.0),riskFactor);


    p1.calculateRiskFactor(.7);
    riskFactor =p1.getRiskFactor();
    assertEquals(Double.valueOf(1.4),riskFactor);

    p1.calculateRiskFactor(.0);
    riskFactor =p1.getRiskFactor();
    assertEquals(Double.valueOf(0.0),riskFactor);

    p1.setNetWorth(5);
    p1.calculateRiskFactor(.75);
    riskFactor = p1.getRiskFactor();
    assertEquals(Double.valueOf(1.875),riskFactor);



  }

//
//  @Test
//  public void testThis() {
//    // mock creation
//    List mockedList = mock(List.class);
//
//    // using mock object - it does not throw any "unexpected interaction" exception
//    mockedList.add("one");
//    mockedList.clear();
//
//    // selective, explicit, highly readable verification
//    verify(mockedList).add("one");
//    verify(mockedList).clear();
//
//
//    Region<String,String> mockedRegion = mock(Region.class);
//    mockedRegion.put("one","two");
//    String str = mockedRegion.get("one");
//    when(mockedRegion.get("one")).thenReturn("first");
//
//    System.out.println(mockedRegion.get("one"));
//    String userId = "1";
//
//RegionOperationHelper service = new RegionOperationHelper();
//    Person p1 = new Person(userId,"Alice","Glass",4);
//
//    Trade t = new Trade("123","AAPL",userId,320);
//
//
//
//  }


}
