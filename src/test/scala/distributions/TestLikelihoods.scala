import org.scalatest.FlatSpec

import breeze.linalg._

class SetSpec extends FlatSpec {

  val mean0 = DenseVector.zeros[Double](3)
  val cov0 = DenseMatrix.zeros[Double](3,3)
  cov0(0,0) = 1
  cov0(1,1) = 1
  cov0(2,2) = 1

  val dist0 = GaussianParameters(mean0,cov0)

  val point0 = DenseVector.zeros[Double](3)
  point0(0) = 1.0

  val likelihood = LikelihoodFunctions.calculateGaussianDensity(dist0,point0)
  println(likelihood)

  // "An empty Set" should "have size 0" in {
  //   assert(Set.empty.size == 0)
  // }
  //
  // it should "produce NoSuchElementException when head is invoked" in {
  //   assertThrows[NoSuchElementException] {
  //     Set.empty.head
  //   }
  // }
}
