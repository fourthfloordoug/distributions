import org.scalatest._

import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.Await

import distributions.Distribution._


class LikelihoodSpec extends FlatSpec with Matchers{

  val precision = 0.001
  val midPrecision = 0.01
  val bigPrecision = 0.1

  val point1 = 0
  val point2 = 0.5
  val point3 = 1.0
  val point4 = 5.0

  val mean1 = 0.0
  val var1 = 1.0
  val g1 = new Gaussian(mean1,var1)
  val g1TruthPt1 = 0.3989
  val g1TruthPt2 = 0.3521
  val g1TruthPt3 = 0.2420
  val g1TruthPt4 = 0.0000

  "A Gaussian" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(g1,point1),1 seconds) should equal (g1TruthPt1 +- precision)
    Await.result(calculateLikelihood(g1,point2),1 seconds) should equal (g1TruthPt2 +- precision)
    Await.result(calculateLikelihood(g1,point3),1 seconds) should equal (g1TruthPt3 +- precision)
    Await.result(calculateLikelihood(g1,point4),1 seconds) should equal (g1TruthPt4 +- precision)
  }

  val weight1 = 0.5
  val g1Comp = new GaussianComponent(g1,weight1)
  val g1CompTruthPt1 = 0.1995
  val g1CompTruthPt2 = 0.1760
  val g1CompTruthPt3 = 0.1210
  val g1CompTruthPt4 = 0.0000

  "A Gaussian Component" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(g1Comp,point1),1 seconds) should equal (g1CompTruthPt1 +- precision)
    Await.result(calculateLikelihood(g1Comp,point2),1 seconds) should equal (g1CompTruthPt2 +- precision)
    Await.result(calculateLikelihood(g1Comp,point3),1 seconds) should equal (g1CompTruthPt3 +- precision)
    Await.result(calculateLikelihood(g1Comp,point4),1 seconds) should equal (g1CompTruthPt4 +- precision)
  }

  val mean2 = 3.0
  val var2 = 1.0
  val g2 = new Gaussian(mean2,var2)
  val g2TruthPt1 = 0.0044
  val g2TruthPt2 = 0.0175
  val g2TruthPt3 = 0.0540
  val g2TruthPt4 = 0.0540

  "A second Gaussian" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(g2,point1),1 seconds) should equal (g2TruthPt1 +- precision)
    Await.result(calculateLikelihood(g2,point2),1 seconds) should equal (g2TruthPt2 +- precision)
    Await.result(calculateLikelihood(g2,point3),1 seconds) should equal (g2TruthPt3 +- precision)
    Await.result(calculateLikelihood(g2,point4),1 seconds) should equal (g2TruthPt4 +- precision)

  }

  val weight2 = 0.5
  val g2Comp = new GaussianComponent(g2,weight2)
  val g2CompTruthPt1 = 0.0022
  val g2CompTruthPt2 = 0.0088
  val g2CompTruthPt3 = 0.0270
  val g2CompTruthPt4 = 0.0270

  "A second Gaussian Component" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(g2Comp,point1),1 seconds) should equal (g2CompTruthPt1 +- precision)
    Await.result(calculateLikelihood(g2Comp,point2),1 seconds) should equal (g2CompTruthPt2 +- precision)
    Await.result(calculateLikelihood(g2Comp,point3),1 seconds) should equal (g2CompTruthPt3 +- precision)
    Await.result(calculateLikelihood(g2Comp,point4),1 seconds) should equal (g2CompTruthPt4 +- precision)
  }


  val gmm1 = new GaussianMixture(List(g1Comp,g2Comp))
  val gmm1TruthPt1 = 0.2017
  val gmm1TruthPt2 = 0.1848
  val gmm1TruthPt3 = 0.1480
  val gmm1TruthPt4 = 0.0270

  "A Gaussian Mixture" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(gmm1,point1),1 seconds) should equal (gmm1TruthPt1 +- precision)
    Await.result(calculateLikelihood(gmm1,point2),1 seconds) should equal (gmm1TruthPt2 +- precision)
    Await.result(calculateLikelihood(gmm1,point3),1 seconds) should equal (gmm1TruthPt3 +- precision)
    Await.result(calculateLikelihood(gmm1,point4),1 seconds) should equal (gmm1TruthPt4 +- precision)
  }


  val mu1 = math.Pi
  val kappa1 = 1.0
  val vm1 = new VonMises(mu1,kappa1)
  val vm1TruthPt1 = 0.0462 //from matlab
  val vm1TruthPt2 = 0.0523 //from matlab
  val vm1TruthPt3 = 0.0732 //from matlab
  val vm1TruthPt4 = 0.0947 //from matlab

  "A vonMises distribution" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(vm1,point1),1 seconds) should equal (vm1TruthPt1 +- precision)
    Await.result(calculateLikelihood(vm1,point2),1 seconds) should equal (vm1TruthPt2 +- precision)
    Await.result(calculateLikelihood(vm1,point3),1 seconds) should equal (vm1TruthPt3 +- precision)
    Await.result(calculateLikelihood(vm1,point4),1 seconds) should equal (vm1TruthPt4 +- precision)

  }

  val nu1 = 5
  val st1 = new StudentT(nu1)
  val st1TruthPt1 = 0.3796 //from matlab
  val st1TruthPt2 = 0.3279 //from matlab
  val st1TruthPt3 = 0.2197 //from matlab
  val st1TruthPt4 = 0.0018 //from matlab

  "A StudentT distribution" should "return appropriate likelihood values" in {

    Await.result(calculateLikelihood(st1,point1),1 seconds) should equal (st1TruthPt1 +- midPrecision)
    Await.result(calculateLikelihood(st1,point2),1 seconds) should equal (st1TruthPt2 +- midPrecision)
    Await.result(calculateLikelihood(st1,point3),1 seconds) should equal (st1TruthPt3 +- midPrecision)
    Await.result(calculateLikelihood(st1,point4),1 seconds) should equal (st1TruthPt4 +- midPrecision)

  }
}
