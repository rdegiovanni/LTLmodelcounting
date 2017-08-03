package main;
import java.math.BigInteger;

import vlab.cs.ucsb.edu.DriverProxy;
import vlab.cs.ucsb.edu.DriverProxy.Option;

public class ABC {

  public static BigInteger count(String formula, long bound) {

    DriverProxy abcDriver = new DriverProxy();
    
//    abcDriver.setOption(Option.ENABLE_IMPLICATIONS);
//    abcDriver.setOption(Option.USE_SIGNED_INTEGERS);
    abcDriver.setOption(Option.REGEX_FLAG,Option.REGEX_FLAG_ANYSTRING);

    String constraint = "(set-logic QF_S)\n"
		+ "(declare-fun x () String)\n"
        + "(assert (in x /"+formula+"/))\n"
        + "(assert (= (len x) "+bound+"))\n"
        + "(check-sat)\n";
    
//    System.out.println(constraint);
//    System.out.println(bound);
    boolean result = abcDriver.isSatisfiable(constraint);
    BigInteger count = BigInteger.ZERO;
    
    if (result) {
      System.out.println("Satisfiable");
      
      count = abcDriver.countVariable("x",bound);

      if (count != null) {
        System.out.println("Number of solutions: " + count.toString());
      } else {
        System.out.println("An error occured during counting, please contact vlab@cs.ucsb.edu");
      }
      
//    byte[] func = abcDriver.getModelCounterForVariable("x"); 
//      BigInteger count2 = abcDriver.countVariable("x", bound, func);
//      System.out.println("cache count: " + count2);
      
//      abcDriver.printResultAutomaton();
      
//      Map<String, String> results = abcDriver.getSatisfyingExamples();
//      for (Entry<String, String> var_result : results.entrySet()) {
//        System.out.println(var_result.getKey() + " : \"" + var_result.getValue() + "\"");
//      }
    } else {
      System.out.println("Unsatisfiable");
    }
    
    abcDriver.dispose(); // release resources
    return count;
  }
}
