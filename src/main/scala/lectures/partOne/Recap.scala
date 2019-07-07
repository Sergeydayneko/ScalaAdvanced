package lectures.partOne

import scala.annotation.tailrec

object Recap extends App {
  val aCondition: Boolean = false
  val aConditionVal = if (aCondition) 42 else 65

  // instructions and expressions

  // compiler infers types for us
  val aCodeBlock  = {
    if (aCondition) 54
    56
  }

  // Unit = void
  val theUnit = println("Hello, Scala")

  // functions
  def aFunctuin(x: Int): Int = x + 1

  // recursion: stack and tail

  @tailrec def factorial(n: Int, acc: Int): Int =
    if (n < 0) acc
    else factorial(n - 1, acc * n)

  // object-oriented programming

  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog // subtyping

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class  Crocodile extends Animal with  Carnivore {
    override def eat(a: Animal): Unit = println("crunch")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog

  // anonumous classes
  val aCarnivar: Carnivore = (a: Animal) => println("crunch")

  // generic
  abstract class MyList[+A] // covariance

  //singltons
  object MyList

  // case classes
  case class Person(name: String, age: Int)

  // exceptions try/catch/finally

  val throwException = throw new RuntimeException //Nothing

  val aPotentuialFailure = try {
    throw new Exception
  } catch {
    case e: Exception => "I caught"
  } finally {
    println("finally")
  }
  
  // packaging and imports
  
  // functional programming :: Функции(Function1) это на самом деле объекты
  val incrementer = new (Int => Int) {
    override def apply(v1: Int): Int = v1 + 1
  }

  List(1, 2, 3).map(incrementer)

  // for-comprehensions

  //analogue of map/flatMap
  val pairs = for {
    num <- List(1, 2, 3) // if condition(guard)
    char <- List('a', 'b', 'c')
  } yield num + " - " + char

  // Scala collections: Sequences, Arrays, Lists, Vectors, Map, Tuples

  val aMap = Map(
    "Dabiel" -> 787,
    "Sergey" -> 277
  )

  // "collections": Options, Try
  val anOption = Some(2)

  // pattern matching

  val x = 2

  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case _ => "Anything"
  }

  val bob = Person("Bob", 22)

  val greeting = bob match {
    case Person(n, _) => s"Hi, my name is $n"
  }

  // all the patterns


  
  

  

}
