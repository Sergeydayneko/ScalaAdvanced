package lectures.partOne

import lectures.partOne.AdvancedPatternMatching.PersonWrapper

object AdvancedPatternMatching extends App {
  val numbers = List(4)

  val desc = numbersForUnapply match {
    case head :: Nil => head
    case _ =>
  }

  /*
    - constants
    - wildcards
    - case classes
    - tuple
    - some special matching like above
   */

  class Person(val name: String, val age: Int)

  // Название компаньон объекта может быть любым, но принято называть по название
  // объекта для которого он делается.
  object Person {
    // метод для использование в паттерн матчинге
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) None
      else Some((person.name, person.age))

    def unapply(age: Int): Option[String] = Some(if (age < 21) "minor" else "major")

  }


  val bob = new Person("Bob", 20)

  val greeting = bob match {
      // внутри скобок возвращаемый объект метода unapply
      // паттерн матчинг принимает объект, который стоит
      // перед match
    case Person(name, age) => s"Hello, my name is $name and age is $age"
    case _ => "No one here"
  }

  val legalStatus = bob.age match {
    case Person(status) => s"My status is $status"

  }

  /*
    Задание номер один. Имена локальных объектов для паттерн матчинга
    обозначаются с маленькой буквы.
   */

  object single {
    def unapply(number: Int): Boolean = number < 10

  }

  // Если результат не нужен, то Option можно опускать
  object even {
    def unapply(number: Int): Boolean = number % 2 == 0
    // Метод ниже сработает так же, но даст возможность использования
    // результата(в данном случае - true)
//    def unapply(number: Int): Option[Boolean] =
//      if (number % 2 == 0) Some(true)
//      else None
  }

  val x = 50

  val whatIsThisNumber = x match {
//    case number @ single(_) => s"Single number is $number" // Условие, когда нужно вывести
    case single() => s"This is Single!"
    case even() => s"This is not single and even number."
    case _ => "Too big number"
  }


//  println(whatIsThisNumber)

  // pattern matching part 2

  case class Or[A, B](a: A, b: B)
  val either = Or(2, "Two")

  // Можем написать case Or(number, string) или number Or string (можно использовать только с 2 аргументами)
  // Короч case класс мы можем разбирать без unapply, где аргументами будут аргменты кейс класса
  val choice = either match {
    case number Or string =>
  }

  val numbersForUnapply = List(1, 2, 3)

  val isOne = numbersForUnapply match {
    case List(1, _*) => "Starting with one"
  }

//  println(isOne)

  // когда создаем геттеры, автоматически появляются поля
  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }

  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] = {
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
    }
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))

  val value = myList match {
    case MyList(1, 2, 3) => "Right List"
    case _ => "Nothing good"
  }

//  println(value)

  // !!! custom return type for unapply
  // Для создания обертки для unapply метода необходимо
  // создать класс, который будет иметь два метода: isEmpty: Boolean и get: T
  // Используется редко
  abstract class Wrapper[T] {
    def isEmpty: Boolean // если закометировать и не овверайдить потом, то обернуть в unapply этот метод будет нельзя
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false
      override def get: String = person.name
    }
  }

  val nameOfPerson = new Person("bob", 25) match  {
    case PersonWrapper(n) => s"His name is $n"
    case _ => "I dont know who is that person"
  }

  println(nameOfPerson)
}
