package io.pivotal.gemfireServer.listeners;

import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEventListener;
import java.util.List;

/**
 * Pivotal Data Engineering
 */

class MyAsyncEventListener implements AsyncEventListener {
  public boolean processEvents(List<AsyncEvent> events) {
    // Process each AsyncEvent
    for(AsyncEvent event: events) {
      // Write the event to a database
    }
    return true;
  }

  @Override
  public void close() {

  }
}