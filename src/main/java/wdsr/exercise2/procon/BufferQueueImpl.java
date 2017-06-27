package wdsr.exercise2.procon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Task: implement Buffer interface using one of *Queue classes from java.util.concurrent package.
 */
public class BufferQueueImpl implements Buffer {
  BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(100000);

  public void submitOrder(Order order) throws InterruptedException {
    try {
      queue.put(order);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public Order consumeNextOrder() throws InterruptedException {
    try {
      if (queue.isEmpty())
        queue.wait();
      else
        return queue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }


}
