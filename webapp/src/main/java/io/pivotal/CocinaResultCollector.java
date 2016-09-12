package io.pivotal;

import com.gemstone.gemfire.cache.execute.FunctionException;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import com.gemstone.gemfire.distributed.DistributedMember;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by mross on 9/9/16.
 */
public class CocinaResultCollector<T,S> implements
    ResultCollector<Serializable, Serializable> {

  int calculatedAverage = 0;
  int resultsCollected = 0;

  @Override
  public Serializable getResult() throws FunctionException {
    return calculatedAverage;
  }

  @Override
  public Serializable getResult(long l, TimeUnit timeUnit)
      throws FunctionException, InterruptedException {
    return this.getResult();
  }

  @Override
  public void addResult(DistributedMember distributedMember,
      Serializable serializable) {

    calculatedAverage += (int)serializable;
    resultsCollected++;

  }

  @Override
  public void endResults() {

  }

  @Override
  public void clearResults() {

  }
}
