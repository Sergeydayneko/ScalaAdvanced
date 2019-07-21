package playground

object ScalaPlayground extends App {

  // pattern matching
//  println(leafSum(List(1, 2, 3, 4, 5)))

  println(anyFunction(List(1, 2, 3)))
  def anyFunction(list: List[Int]): Unit =
      list match {
            // ...methods that have already been shown
          case first :: second :: Nil  => println(s"List has only 2 elements: $first and $second")
          case first :: second :: tail => println(s"First: $first \nSecond: $second \nTail: $tail")
    }

  def leafSum(list: List[_]): Int = {
    list match {
      case list if list.isEmpty => 0
      case (head: Int) :: tail => head + leafSum(tail)
    }
  }


//  def swap(list: List[Int], set: Set[Int]): Unit = {
//    set match  {
//      case l :: ll =>
//    }
//    list match  {
//
//      case head :: body :: tail => println(s"$head ::: $body ::: $tail")
//    }
//  }

  // #1
  def NumbersSwap(nums: (Int, Int)): (Int, Int) = {
    nums match {
      case (first, last) => (last, first)
      case _ => throw new Exception("Wrong input format")
    }
  }

  // #1
  "Mississippi".zipWithIndex.foldLeft(Map[Char, Set[Int]]())((acc, next) => {
    if (!acc.contains(next._1)) acc + (next._1 -> Set(next._2))
    else acc + (next._1 -> (acc(next._1) + next._2))
  }).foreach(_._2.toList.sorted.toSet)


  // #4
  val names = List("Tom", "Fred", "Harry")
  val nums = Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)

  names.foldLeft(List[Int]())((acc, next) => {
    if (nums.contains(next)) acc :+ nums(next)
    else acc
  })


  // #5
  def mkMyString[T](list: List[T], separator: String): String = {
    list.foldLeft("")((acc, next) => {
      if (list.lastIndexOf(next) == list.length - 1) acc + next
      else acc + next + separator
    })
  }

  // #7
  List(2, 3, 4) zip List(15, 25, 50) map Function.tupled(_*_)

  // #8
  makeMatrix(List(1, 2, 3, 4, 5, 6, 7, 8, 9), 3)

  def makeMatrix(arr: List[Int], columns: Int): List[List[Int]] = {
    arr.grouped(columns).toList
  }






}
