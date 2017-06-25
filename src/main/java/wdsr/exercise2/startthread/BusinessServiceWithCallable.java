package wdsr.exercise2.startthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BusinessServiceWithCallable {
  private final ExecutorService executorService;
  private final NumericHelper helper;

  public BusinessServiceWithCallable(ExecutorService executorService, NumericHelper helper) {
    this.executorService = executorService;
    this.helper = helper;
    executorService = Executors.newFixedThreadPool(100);
  }

  /**
   * Calculates a sum of 100 random numbers. Random numbers are returned by helper.nextRandom
   * method. Each random number is calculated asynchronously.
   * 
   * @return sum of 100 random numbers.
   */
  public class CallableClass implements Callable<Integer> {

    public Integer call() {
      return helper.nextRandom();
    }

  }

  public long sumOfRandomInts() throws InterruptedException, ExecutionException {
    long result = 0;

    // TODO Task:
    // 1. create 100 Callable objects that invoke helper.nextRandom in their call() method.
    // 2. submit all Callable objects to executorService (executorService.submit or
    // executorService.invokeAll)
    // 3. sum up the results - each random number can be retrieved using future.get() method.
    // 4. return the computed result.

    List<Future<Integer>> list = new ArrayList<Future<Integer>>();
    Callable<Integer> callable = new CallableClass();

    for (int i = 0; i < 100; i++) {
      Future<Integer> future = executorService.submit(callable);
      list.add(future);
    }

    for (Future<Integer> f : list) {
      result += f.get();
    }

    executorService.shutdown();
    return result;
  }
}
