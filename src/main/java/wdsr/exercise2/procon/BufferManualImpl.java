package wdsr.exercise2.procon;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Buffer interface without using any *Queue classes from java.util.concurrent
 * package. Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is
 * allowed.
 */
public class BufferManualImpl implements Buffer {
  private final Lock lock = new ReentrantLock();
  private final Condition isEmpty = lock.newCondition();
  private final Condition isFull = lock.newCondition();

  Queue<Order> queue = new PriorityQueue<Order>(100000);

  public void submitOrder(Order order) throws InterruptedException {
    lock.lock();
    try {
      queue.offer(order);
    } finally {
      lock.unlock();
    }
  }

  public Order consumeNextOrder() throws InterruptedException {
    lock.lock();
    Order result;
    try {
      result = queue.poll();
    } finally {
      lock.unlock();
    }
    return result;
  }
}
