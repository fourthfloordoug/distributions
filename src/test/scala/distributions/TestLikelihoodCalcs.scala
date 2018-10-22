import org.scalatest.FlatSpec

import distributions.Distribution._

class TestLikelihoodCalcs extends FlatSpec {

  val point1 = 0;
  val point2 = 0.5;
  val point3 = 1.0;
  val point4 = 5.0;
  val point5 = -1.0;

  val g1 = new Gaussian(0, 1.0)
  val l1 = calculateLikelihood(g1,point1)
  println("Gaussian Likelihood calculation = " + l1)

  val gc1 = new GaussianComponent(g1,0.5)
  val l2 = calculateLikelihood(gc1,point1)
  println("Gaussian Component likelihood calculation = " + l2)

  val listOfComponents = List(gc1,new GaussianComponent(new Gaussian(5,1.0),0.5))
  val gmm1 = new GaussianMixture(listOfComponents)
  val l3 = calculateLikelihood(gmm1,point1)
  println("Gaussian Mixture likelihood calculation = " + l3)

  val vm1 = new VonMises(3.14,1)
  val l4 = calculateLikelihood(vm1,point1)
  println("Von Mises likelihood calculation = " + l4)

  val st1 = new StudentT(5)
  val l5 = calculateLikelihood(st1,point1)
  println("Student T Likelihood calculation = " + l5)
}
