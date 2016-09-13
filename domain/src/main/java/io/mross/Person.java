package io.mross;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

import java.util.ArrayList;

@Region("People")
public class Person {


  @Id private String id;

  private String firstName;
  private String lastName;
  private ArrayList<String> trades;
  private int netWorth;
  private Double riskFactor;

  @PersistenceConstructor
  public Person() {
    this.trades = new ArrayList<String>();
    this.riskFactor = 1.0;
  }

  @PersistenceConstructor
  public Person(String id, String firstName, String lastName, int netWorth) {
    this();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.netWorth = netWorth;
  }

  public ArrayList<String> getTrades() {
    return trades;
  }

  public void addTrade(String tradeId) {
      trades.add(tradeId);
  }

  public int getNetWorth() {
    return netWorth;
  }

  public void setNetWorth(int netWorth) {
    this.netWorth = netWorth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Double getRiskFactor() {
    return riskFactor;
  }

  public void calculateRiskFactor(Double risk) {
    riskFactor = ((netWorth* risk))/2;
  }

  @Override public String toString() {
    return "Person{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", trades='" + trades + '\'' +
        ", netWorth=" + netWorth +
        '}';
  }
}
