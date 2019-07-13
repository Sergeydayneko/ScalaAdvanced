package lectures.partTwo

import scala.annotation.tailrec


trait MySet[A] extends (A => Boolean) {
  override def apply(v1: A): Boolean = contains(v1)
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(set: MySet[A]): MySet[A]

  def map[B](transform: A => B): MySet[B]
  def flatMap[B](transform: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def forEach(func: A => Unit): Unit

}
class EmptySet[B] extends MySet[B] {
  override def contains(elem: B): Boolean = false

  override def +(elem: B): MySet[B] = new NoneEmptySet(elem, this)

  override def ++(set: MySet[B]): MySet[B] = set

  override def map[T](transform: B => T): MySet[T] = new EmptySet[T]

  override def flatMap[T](transform: B => MySet[T]): MySet[T] = new EmptySet[T]

  override def filter(predicate: B => Boolean): MySet[B] = this

  override def forEach(func: B => Unit): Unit = ()

}

class NoneEmptySet[B](head: B, tail: MySet[B]) extends MySet[B] {
  override def contains(elem: B): Boolean = head == elem || tail.contains(elem)

  override def +(elem: B): MySet[B] = {
    if (contains(elem)) this
    else new NoneEmptySet[B](elem, this)
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



