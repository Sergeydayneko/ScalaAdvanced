package lectures.partTwo

import scala.collection.generic.FilterMonadic

object LazyEvaluation  extends App {

  // lazy DELAYS the evaluation
  lazy val x: Int = throw new RuntimeException

  // Когда вызываем ByName, то функция получения числа
  // будет вызвана 3 раза.
  def byNameMethod(n: => Int) = {
    // Для решения множественной инициализаци используем lazy
    // CALL BY NEED
    lazy val t = n // only evaluates ones
    t + t  + t + 1
  }
  def retrieveMagicalNumber = {
    println("hello!")
    42
  }

//  println(byNameMethod(retrieveMagicalNumber))

  // filtering with lazy vals
  def lessThan30(i: Int): Boolean = {
    println(s"$i is less than 30?")
    i < 30
  }

  def greaterThen20(i: Int): Boolean = {
    println(s"$i is greater then 20?")
    i > 20
  }

  val numbers = List(1, 25, 40, 5, 23)
  val lt30 = numbers filter lessThan30
  val gt20 = lt30 filter greaterThen20
//  println(gt20)

  val lt30Lazy: FilterMonadic[Int, List[Int]] = numbers.withFilter(lessThan30) // use lazy values under the hood
  val gt20Lazy = lt30Lazy withFilter greaterThen20


//  println(gt20Lazy) //terriffic string

  gt20Lazy foreach println

  // for-comprehension use withFilter with guargs
  for {
    a <- List(1, 2, 3) if a % 2 == 0
  } yield a + 1

  // converts to
  List(1, 2, 3).withFilter(_ % 2 == 0).map(_ + 1)

  abstract class MyStream[+A] {

  }

}
