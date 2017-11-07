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
  println()

  val mean1 = DenseVector.zeros[Double](3)
  val cov1 = DenseMatrix.zeros[Double](3,3)
  mean1(0) = 1
  mean1(1) = 1
  mean1(2) = 1
  cov1(0,0) = 1
  cov1(1,1) = 1
  cov1(2,2) = 1

  val dist1 = GaussianParameters(mean1,cov1)

  val paramList = List(dist0,dist1)
  val weightList = List(0.5,0.5)

  val mixture0 = GaussianMixtureParameters(weightList,paramList)

  val densities = mixture0.componentParams.map(component => LikelihoodFunctions.calculateGaussianDensity(component,point0))
  println(densities)
  val zippedComponents = mixture0.weights.zip(densities)
  println(zippedComponents)
  val weightedDensities = zippedComponents.map(weightAndDensity => weightAndDensity._1 * weightAndDensity._2)
  println(weightedDensities)
  val sumLikelihoods = weightedDensities.sum
  println(sumLikelihoods)
  println()

  val mixtureLikelihood = LikelihoodFunctions.calculateGaussianMixtureDensity(mixture0,point0)
  println(mixtureLikelihood)

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
