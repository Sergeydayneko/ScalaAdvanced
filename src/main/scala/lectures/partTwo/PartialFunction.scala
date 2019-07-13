package lectures.partTwo

object PartialFunction extends App {

  val pf: PartialFunction[Int, Int] = {
    case 1 => 2
    case 3 => 4
    case 5 => 43
  }

  println(pf(1))

  // PF utils
  pf.isDefinedAt(4)

  // lift
  val lifted = pf.lift // Теперь частичная функция возвращает Option

  val chained = pf.orElse[Int, Int] {
    case 32 => 43
  } // цепочка частичных функций. Если не находит в первой цепочке, идет во вторую

  // Мы можем объявлять частичные функции как обычные функции,
  // так как они являются подтипом обычных функций
  val normalFunction: Int => Int = {
    case 4 => 5
  }
  // В следствии этого, функции высшего порядка без проблем принимают
  // частичные функции

  val mappedList = List(1, 3, 5).map(pf)

  /*
    Note:
    1. Частичные функции могут иметь только один параметр
   */

  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hello you tooo!"
    case "Goodbye" => "Goodbye you tooo!"
    case _ => "I dont has any idea about words"
  }

  scala.io.Source.stdin.getLines().foreach(str =>println(s"Chatbot says ${chatBot(str)}") )

}
