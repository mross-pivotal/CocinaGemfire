package io.pivotal.gemfireServer.listeners;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.cache.*;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.util.CacheListenerAdapter;
import io.mross.Person;
import io.mross.Trade;

import java.util.Properties;

/**
 * Pivotal Data Engineering
 */

public class CacheListener extends CacheListenerAdapter<String, Trade>
    implements Declarable {

  private static LogWriter logger;
  private static Cache cache;
  private static Region <String,Person>peopleRegion;


  static {
    logger = CacheFactory.getAnyInstance().getDistributedSystem()
        .getLogWriter();

    cache = CacheFactory.getAnyInstance();
    peopleRegion = cache.getRegion("People");
  }

  public CacheListener(){}

  @Override
  public void afterCreate(EntryEvent<String, Trade> entryEvent) {
    logger.info("In after Create for Trades");
    String key = entryEvent.getKey();
    addToIdToUser(entryEvent);


  }

  @Override
  public void afterUpdate(EntryEvent<String, Trade> entryEvent) {
    logger.info("In after Update for Trades");

    addToIdToUser(entryEvent);

  }


  private void addToIdToUser(EntryEvent<String, Trade> entryEvent) {

    Trade trade = entryEvent.getNewValue();
    String userId = trade.getUserId();

    try {
      Person person = peopleRegion.get(userId);
      person.addTrade(trade.getId());
      peopleRegion.put(person.getId(),person);
    } catch (Exception e) {
      logger.info("Person was not found");
    }

  }




  @Override
  public void init(Properties props) {
    logger.info("I am in " + this.getClass().getSimpleName() + " class init");
  }

}
