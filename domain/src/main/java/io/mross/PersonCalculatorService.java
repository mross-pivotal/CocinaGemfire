package io.mross;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mross on 9/9/16.
 */
public class PersonCalculatorService {



  public PersonCalculatorService() {

  }

  public int calculateAverage(ArrayList<Person> people) {
    int calculatedAverage = 0;
    int sum =0;

    if (people.size()==0) {
      return 0;
    }

    for (Person p: people) {
      sum += p.getNetWorth();
    }

    calculatedAverage = sum / people.size();

    return calculatedAverage;
  }


}
