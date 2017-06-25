package wdsr.exercise2.counter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Marek on 05.03.2016.
 * 
 * Task: use 'synchronized' keyword in this file to make SimpleCountingFacadeTest pass.
 */
public class SimpleCountingFacade implements CountingFacade {
  private final BusinessService businessService;

  private AtomicInteger invocationCounter;

  public SimpleCountingFacade(BusinessService businessService) {
    this.businessService = businessService;
    invocationCounter = new AtomicInteger();
  }

  public void countAndInvoke() {
    invocationCounter.incrementAndGet();
    businessService.executeAction();
  }

  public int getCount() {
    return invocationCounter.intValue();
  }
}
