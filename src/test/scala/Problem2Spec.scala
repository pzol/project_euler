import scala.math.BigInt
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class Problem2Spec extends FlatSpec with ShouldMatchers {
  "Problem 2" should """Each new term in the Fibonacci sequence is generated by adding the previous two terms. 
                        By starting with 1 and 2, the first 10 terms will be:

                        1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

                        By considering the terms in the Fibonacci sequence whose values do not exceed four million, 
                        find the sum of the even-valued terms.""" in {


    def fib_(max: Long) = (1L until max).foldLeft(1L :: 1L :: Nil) { (sum,_) => (sum.head + sum.tail.head) :: sum }.reverse
    fib_(10) should be === List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)

    var fib = 0 :: 1 :: Nil; while(fib.head < 4000000) { fib = (fib.head + fib.tail.head) :: fib }
    fib.tail.filter( _ % 2 == 0).sum should be === 4613732

    lazy val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map { n => n._1 + n._2 }
    fibs.filter {_ % 2 == 0}.takeWhile {_ < 4000000}.sum should be === 4613732
  }
}
