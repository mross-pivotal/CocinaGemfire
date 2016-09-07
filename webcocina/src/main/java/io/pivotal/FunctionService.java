package io.pivotal;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mross on 9/6/16.
 */

@Component
public class FunctionService {

  @Autowired
  ClientCache cache;

  public String sayHi(){
    Execution functionExecution = com.gemstone.gemfire.cache.execute.FunctionService
        .onRegion(cache.getRegion("test"));

    ResultCollector<?,?> results = functionExecution.execute("GemfireFunction");

    List<?> list = (List<?>)results.getResult();
    return list.toString();
  }
}
