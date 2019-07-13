object Variative extends App {

  case class Person(name: String)
  class Student(name: String, val faculty: String) extends Person(name)

  case class Pair[T >: Person](f: T, s: T)

  def testPair(arg: Pair[Person]) = println(arg.f.name)

  val personPair = Pair(Person("serj"), Person("vika"))
  val studentPair = Pair(new Student("Vika", "it"), new Student("vika", "chem"))


}
