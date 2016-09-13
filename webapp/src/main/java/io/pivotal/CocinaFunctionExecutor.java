package io.pivotal;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mross on 9/6/16.
 */

@Component
public class CocinaFunctionExecutor {

  @Autowired
  ClientCache cache;

  public int runCalculateAverage(){
    CocinaResultCollector<?, ?> cocinaResultCollector =  new CocinaResultCollector<>();

    Execution functionExecution = FunctionService
        .onRegion(cache.getRegion("People")).withCollector(
            cocinaResultCollector);

    ResultCollector<?,?> results = functionExecution.execute("CalculateAverageFunction");
    Object resultWrapper = results.getResult();
    int calculatedAverage = (int) resultWrapper;

    return calculatedAverage;
  }

  public void runCalculateDailyRisk() {
    Execution functionExecution = FunctionService
        .onRegion(cache.getRegion("People"));
    ResultCollector<?,?> results = functionExecution.execute("GemfireFunction");


  }

}
