package io.pivotal;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.gemstone.gemfire.cache.Region;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.client.Pool;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;

/**
 * Invokes a registered function using GemFire's {@link FunctionService}
 * @author David Turanski
 *
 */
@Component
public class MyFunctionExecution {



  public String sayHi() {
    return "HIII";
  }

//  public String execute() {
//
//    Execution functionExecution = CocinaFunctionExecutor.onRegion(testRegion);
//
//    ResultCollector<?,?> results = functionExecution.execute("GemfireFunction");
//
//    List<?> list = (List<?>)results.getResult();
//
//    return ((String)list.get(0));
//  }
}