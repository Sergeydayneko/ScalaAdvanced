package lectures.partOne

object AdvancedPatternMatching extends App {
  val numbers = List(4)

  val desc = numbers match {
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


  println(whatIsThisNumber)

  // pattern matching part 2 next
}
