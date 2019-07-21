package lectures.partTwo

import scala.annotation.tailrec

// я проставляю везде разные типизированные буквы, но можно одни и те же
// разницы никакой
trait MySet[A] extends (A => Boolean) {
  override def apply(v1: A): Boolean = contains(v1)
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(set: MySet[A]): MySet[A]
  def -(elem: A): MySet[A]

  def map[B](transform: A => B): MySet[B]
  def flatMap[B](transform: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def forEach(func: A => Unit): Unit
  def &(set: MySet[A]): MySet[A]
  def --(set: MySet[A]): MySet[A]

//  def unary_! : MySet[A]
}
class EmptySet[B] extends MySet[B] {
  override def contains(elem: B): Boolean = false

  override def +(elem: B): MySet[B] = new NoneEmptySet(elem, this)

  override def ++(set: MySet[B]): MySet[B] = set

  override def -(elem: B): MySet[B] = this

  override def map[T](transform: B => T): MySet[T] = new EmptySet[T]

  override def flatMap[T](transform: B => MySet[T]): MySet[T] = new EmptySet[T]

  override def filter(predicate: B => Boolean): MySet[B] = this

  override def forEach(func: B => Unit): Unit = ()

  override def &(set: MySet[B]): MySet[B] = this

  override def --(set: MySet[B]): MySet[B] = this

//  override def unary_! : MySet[B] = new AllInclusiveSet[B]

}
// ---- Решаем проблему negation оператора
// potentially infinite set
// Из-за того, что мы не можем определить все функции, то необходимо придумать более гибкий подхож
//class AllInclusiveSet[C] extends MySet[C] {
//  override def contains(elem: C): Boolean = true
//
//  override def +(elem: C): MySet[C] = this
//
//  override def ++(set: MySet[C]): MySet[C] = this
//
//  override def -(elem: C): MySet[C] = ???
//
//  override def map[B](transform: C => B): MySet[B] = ???
//  override def flatMap[B](transform: C => MySet[B]): MySet[B] = ???
//
//  override def filter(predicate: C => Boolean): MySet[C] = ???
//  override def forEach(func: C => Unit): Unit = ???
//
//  override def &(set: MySet[C]): MySet[C] = filter(set)
//
//  override def --(set: MySet[C]): MySet[C] = filter(!set)
//
//  override def unary_! : MySet[C] = new EmptySet[C]
//}

//class PropertyBasedSet[A](property: A => Boolean) extends MySet[A]{
//
//}

// -----------

class NoneEmptySet[B](head: B, tail: MySet[B]) extends MySet[B] {
  override def contains(elem: B): Boolean = head == elem || tail.contains(elem)

  override def +(elem: B): MySet[B] = {
    if (contains(elem)) this
    else new NoneEmptySet[B](elem, this)
  }

  override def -(elem: B): MySet[B] = {
    if (elem == head) tail
    else (tail - elem) + head
  }

  /*
   [1 2 3] ++ [4 5] =
   [2 3] ++ [4 5] + 1
   [3] ++ [4 5] + 1 + 2
   [] ++ [4 5] + 1 + 2 + 3
   [4 5] + 1 + 2 + 3
   */
  override def ++(set: MySet[B]): MySet[B] = {
    tail ++ set + head
  }

  override def map[T](func: B => T): MySet[T] = (tail map func) + func(head)

  override def flatMap[T](func: B => MySet[T]): MySet[T] = {
    (tail flatMap func) ++ func(head)
  }

  override def filter(predicate: B => Boolean): MySet[B] = {
    val filteredTail = tail filter predicate
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  override def forEach(func: B => Unit): Unit = {
    func(head)
    tail forEach func
  }

  override def &(set: MySet[B]): MySet[B] = {
    set filter contains
    // filter(set) - потом что set это функция, где apply == contains
  }

  override def --(set: MySet[B]): MySet[B] = {
    set filter(!contains(_))
//    filter(!set)
  }

//  override def unary_! : MySet[B] = !this


  /*
    EXERCISE
    - removing an element
    - &
    - --
   */

}

object MySet {
  def apply[A](args: A*): MySet[A] = {
    @tailrec
    def buildSet(set: Seq[A], acc: MySet[A]): MySet[A] =
      if (set.isEmpty) acc
      else buildSet(set.tail, acc + set.head)

    buildSet(args.toSeq, new EmptySet[A])
  }
}

object Kkek extends App {

  val set = MySet(1, 2, 3, 4)
  val set2 = MySet(2, 3)

  set -- set2 forEach println


}



