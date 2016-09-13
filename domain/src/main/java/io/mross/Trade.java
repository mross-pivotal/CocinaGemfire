package io.mross;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

/**
 * Created by mross on 9/12/16.
 */


@Region("Trades")
public class Trade {

  @Id
  private String id;

  private String symbol;
  private String userId;
  private int amount;

  @PersistenceConstructor
  public Trade() {

  }

  @PersistenceConstructor
  public Trade(String id, String symbol, String userId, int amount) {
    this.id = id;
    this.symbol = symbol;
    this.userId = userId;
    this.amount = amount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Trade{" +
        "id='" + id + '\'' +
        ", symbol='" + symbol + '\'' +
        ", userId='" + userId + '\'' +
        ", amount=" + amount +
        '}';
  }
}
