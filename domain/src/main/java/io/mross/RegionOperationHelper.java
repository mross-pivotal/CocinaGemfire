package io.mross;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.Region;

/**
 * Created by mross on 9/13/16.
 */
public class RegionOperationHelper {
//  private static LogWriter logger;
//
//  static {
//    logger = CacheFactory.getAnyInstance().getDistributedSystem()
//        .getLogWriter();
//  }

  public RegionOperationHelper() {}

  public void addToIdToUser(Trade trade, Region<String,Person> peopleRegion) {

    String userId = trade.getUserId();

    try {
      Person person = peopleRegion.get(userId);
      person.addTrade(trade.getId());
      peopleRegion.put(person.getId(),person);
    } catch (Exception e) {
      System.out.println("Person was not found");
    }

  }

}
