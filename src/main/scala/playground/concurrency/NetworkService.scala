package playground.concurrency

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.{ConcurrentLinkedQueue, Executor, Executors}

object NetworkService extends App {
  val myQueue = new ConcurrentLinkedQueue[Int]()
  val es = Executors.newFixedThreadPool(4)
  val flag = new AtomicBoolean(true)

  val producer: Runnable = () => {
    (1 to 10).foreach(num => {
//      println(num)
      myQueue.add(num)
      Thread.sleep(200)
    })
    flag.set(false)
  }

  val consumer: Runnable = () => {
    var number: Int = 0
    while(flag.get() || myQueue.size() > 0) {
      if ((number = myQueue.poll()) != null && number != 0) {
        println(s"Get element $number")
//        Thread.sleep(500)
      }
    }
  }


  es.execute(producer)
  es.execute(consumer)
  es.shutdown()
}



