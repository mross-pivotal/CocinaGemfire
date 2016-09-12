package io.pivotal;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;
import io.mross.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mross on 9/6/16.
 */

@Controller
public class CocinaController {

  @Autowired ClientCache clientCache;

  @RequestMapping(method = RequestMethod.GET, path = "/getInfo")
  @ResponseBody
  String getInfo() {
    Execution functionExecution = FunctionService
        .onRegion(clientCache.getRegion("People"));

    ResultCollector<?,?> results = functionExecution.execute("CalculateAverageFunction");

    Person person = new Person();
    List<?> list = (List<?>)results.getResult();

    return list.toString();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/transformString")
  @ResponseBody
  String sayHello(@RequestParam(value = "myString", required = true) String myString) {

    return myString.toUpperCase();
  }
}
