import org.scalatest.FlatSpec

import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.Await

import distributions.Distribution._

class LikelihoodSpec extends FlatSpec {

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

  val weight1 = 0.5
  val g1Comp = new GaussianComponent(g1,weight1)
  val g1CompTruthPt1 = 0.1995
  val g1CompTruthPt2 = 0.1760
  val g1CompTruthPt3 = 0.1210
  val g1CompTruthPt4 = 0.0000


  val mean2 = 3.0
  val var2 = 1.0
  val g2 = new Gaussian(mean2,var2)
  val g2TruthPt1 = 0.0044
  val g2TruthPt2 = 0.0175
  val g2TruthPt3 = 0.0540
  val g2TruthPt4 = 0.0540

  val weight2 = 0.5
  val g2Comp = new GaussianComponent(g2,weight2)
  val g1CompTruthPt1 = 0.0022
  val g1CompTruthPt2 = 0.0088
  val g1CompTruthPt3 = 0.0270
  val g1CompTruthPt4 = 0.0270




  val gmm1 = new GaussianMixture(List(g1Comp,g2Comp))
  val gmm1TruthPt1 = 0.2017
  val gmm1TruthPt2 = 0.1848
  val gmm1TruthPt3 = 0.1480
  val gmm1TruthPt4 = 0.0270




  val mu1 = math.Pi
  val kappa1 = 1.0
  val vm1 = new VonMises(mu1,kappa1)
  val vmTruthPt1 = 0.0462 //from matlab
  val vmTruthPt2 = 0.0523 //from matlab
  val vmTruthPt3 = 0.0732 //from matlab
  val vmTruthPt4 = 0.0947 //from matlab

  val nu1 = 5
  val st1 = new StudentT(nu1)
  val st1TruthPt1 = 0.3796 //from matlab
  val st1TruthPt2 = 0.3279 //from matlab
  val st1TruthPt3 = 0.2197 //from matlab
  val st1TruthPt4 = 0.0018 //from matlab

}
