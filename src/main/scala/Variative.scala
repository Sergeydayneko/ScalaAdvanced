import scala.math._

object Variative extends App {


  def fact(n: Int, acc: Int): Int = if (n == 1) acc else fact(n - 1, acc * n)


  def evaluateNumber(n: Double): BigDecimal = {
    (1 to 8).foldLeft(BigDecimal(1.0 + n))((acc, next) =>
       acc + BigDecimal(pow(n, next) / fact(next, 1)).setScale(4, BigDecimal.RoundingMode.HALF_UP)
    )
  }

}
