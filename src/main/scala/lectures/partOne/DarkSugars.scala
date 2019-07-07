package lectures.partOne

import java.util.concurrent.Executors

import scala.util.Try

object DarkSugars extends App {
  // syntax sugar #`: methods with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val desc = singleArgMethod {
    // write some code
    42
  }

  val aTryMethod = Try { // java's try { ... }
    throw new RuntimeException
  }

  List(1, 2, 3).map { x => x + 1 }

  // syntax sugar #2: singel abstract method

  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = x => x + 1

  // example: Runnable

  val aThread: Runnable = () => {
  }

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbsractInstance: AnAbstractType = _ => println("232")

  // syntax sugar #3:  the :: methods

  val preparedList = 1 :: 2 :: List(3, 4)

//  val qq = List(3, 4).::(2).::(1) // the same result

  // 2.::List()
  //  right associative operation ||| Если двоеточие справа, то операция правоассоциативная

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // will be right associative too
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // syntax sugar #4: multi-word method naming
  class TeenGirl(name: String) {
    def `and then she said`(gossip: String) = println(s"name said $name")
  }

  val lilly = new TeenGirl("Lilly")
  lilly.`and then she said`("Scala is so sweet")

  // syntax sugar #5: infix types
  class Composite[A, B]

  val composite: Int Composite String = ???

  class -->[A, B]
  val towards: Int --> String = ???

  // syntax sugar #6 update() method is very special, much like apply()
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 /// rewritten to anArray.update(2, 7) - первый - индекс, второй  значение
  // used in mutable collections

  // remember apply() AND update()!

  // syntax sugar $7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO
    def member = internalMember //getter
    def member_=(value: Int) = // setter
      internalMember = value
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as aMutableContainer.member_=(42)

}
