package lectures.partTwo

object Curry extends App {

  val superAdder: Int => Int => Int =  x => y => x + y

  //curried
  val curried = superAdder(3)

  // METHOD!
  def curriedAdder(x: Int)(y: Int): Int = x + y

  // Без указания типа компилятор не даст возможности произвести карирование
//  val qq = curried(3) // error/
//  val qq = curried(3) _ // ОК тоже, _ говорит компилятору провести eta-expansion
  val qq: Int => Int = curriedAdder(3) // error

  // lifting - transforming method into a function

  // possibility functions
  val simpleAddFunc: Int => Int => Int = x => y => x + y
  val simpleAddFuncAnother = (x: Int, y: Int) => x + y
  def addFunc(x: Int, y: Int) = x + y
  def curriedAddFunc(x: Int)(y: Int) = x + y

  // add 7 variants
  val add7 = (x: Int) => simpleAddFunc(7)(x)

  val add7_1 = (x: Int) => simpleAddFuncAnother(7, x)
  val add7_2 = simpleAddFuncAnother.curried(7)
  val add7_3 = curriedAddFunc(7)(_) // _ - mock
  val add7_4 = addFunc(7, _: Int) // curring too

    // power of underscore
  def concat(x: String, y: String, z: String) = x + y + z
  val curriedConcat = concat("Hello, mr ", _: String, ". You are welcome") // curried function
  val curriedConcat_2 = concat("Hello, mr ", _: String, _: String) // eta-expansion to (x: String, y: String) => concat("hello", x, y)

  // formatter

    "%2.6".format(1.345554353543)
 }
