package playground

import scala.annotation.tailrec

abstract class MyStream[+A] {
  def isEmpty: Boolean
  def head: A
  def tail: MyStream[A]

  def #::[B >: A](element: B): MyStream[B]
  def ++[B >: A](anotherStream: MyStream[B]): MyStream[B]

  def foreach[B](f: B => Unit): Unit
  def map[B](f: A => B): MyStream[B]
  def flatMap[B](f: A => MyStream[B]): MyStream[B]
  def filter(predicate: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A]
  def takeAsList(n: Int): List[A]

  @tailrec
  final def toList[B >: A](acc: List[B] = Nil): List[B] =
    if (isEmpty) acc.reverse
    else tail.toList(head :: acc)
}

object EmptyStream extends MyStream[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyStream[Nothing] = throw new NoSuchElementException

  override def #::[B >: Nothing](element: B): MyStream[B] = new Cons[B](element, this)

  override def ++[B >: Nothing](anotherStream: MyStream[B]): MyStream[B] = anotherStream

  override def foreach[B](f: B => Unit): Unit = ()

  override def map[B](f: Nothing => B): MyStream[B] = this

  override def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this

  override def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this

  override def takeAsList(n: Int): List[Nothing] = Nil
}

class Cons[+A](hd: A, tl: => MyStream[A]) extends MyStream[A] {
  def isEmpty: Boolean = false

  override val head: A = hd
  override lazy val tail: MyStream[A] = tl // вызов по необходимости

  /*
    val s = new Cons(1, EmptyStream)
    val stream = 1 #:: s = new Cons(1, s)
   */
  def #::[B >: A](element: B): MyStream[B] = new Cons[B](element, this)
  def ++[B >: A](anotherStream: MyStream[B]): MyStream[B] = new Cons[B](head, tail ++ anotherStream) //lazy evaluated too

  def foreach[B](f: B => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  /*
    s = new Const(1, ?)
    mapped = s.map(_ + 1) = new Const(2, s.tail.map(_ + 1)) // no use uness
      ... mapped.tail - here evaluation start
   */
  def map[B](f: A => B): MyStream[B] = new Cons[B](f(head), tail.map(f)) // preserves lazy evaluation
  def flatMap[B](f: A => MyStream[B]): MyStream[B] = {
    f(head) ++ flatMap(f)
  }
  def filter(predicate: A => Boolean): MyStream[A] = {
    if(predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate) // preserve lazy evaluation
  }

  def take(n: Int): MyStream[A] = {
    if (n <= 0) EmptyStream
    else if (n == 1) new Cons(head, EmptyStream)
    else new Cons(head, tail.take(n - 1))
  }

  def takeAsList(n: Int): List[A] = take(n).toList()
}

object MyStream {
  /*
    val x = MyStream.from(1)(_ + 1)
    Каждый раз при запросе данных мы задействуем функцию для получения данных
   */
  def from[A](start: A)(generator: A => A): MyStream[A] = {
    new Cons(start, MyStream.from(generator(start))(generator)) // вся суть в хвосте, при том же функции take
  }
}

object LazyCollection extends App {

}
