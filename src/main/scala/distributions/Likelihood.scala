package distributions

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.event.Logging
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.Await
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._

import distributions.Distribution._

import cern.jet.math.Bessel

import scala.util.Failure
import scala.util.Success


trait Likelihood[T] {
  def getLikelihood(t: T, x: Double): Future[Double]
}

object Likelihood {

  implicit val ec = global

  implicit object GaussianLikelihood extends Likelihood[Gaussian] {

    def getLikelihood(t: Gaussian, x: Double): Future[Double] = Future{

      val meanCentered = x - 1.0 * t.mean
      val exponent = (meanCentered * meanCentered / t.variance) * -0.5
      val normalization = 1.0 / scala.math.sqrt(2.0 * scala.math.Pi * t.variance)
      normalization * scala.math.exp(exponent)
    }
  }

  implicit object GaussianComponentLikelihood extends Likelihood[GaussianComponent] {

    def getLikelihood(t: GaussianComponent,x: Double): Future[Double] =


      calculateLikelihood(t.gaussian,x).map(likelihood => t.weight*likelihood)
  }


  implicit object GaussianMixtureLikelihood extends Likelihood[GaussianMixture] {

    def getLikelihood(t: GaussianMixture, x: Double): Future[Double] = {

      Future.sequence(t.components.map(component => calculateLikelihood(component,x))).map(_.sum)
    }
  }

  implicit object vonMisesLikelihood extends Likelihood[VonMises] {

    def getLikelihood(t: VonMises, x: Double): Future[Double] = Future {

      val kappaBessel = Bessel.i0(t.kappa)
      val denom = 2.0 * math.Pi * kappaBessel

      val exponent = t.kappa * (x - t.mu)

      math.exp(exponent) / denom
    }
  }

  implicit object studentTLikelihood extends Likelihood[StudentT] {

    def getLikelihood(t: StudentT, x: Double): Future[Double] = Future{

      val nuPlus1Over2 = (t.nu + 1.0)/2.0
      val nuOver2 = t.nu / 2.0

      val gammaNuPlus1Over2 = stGamma(nuPlus1Over2)
      val gammaNuOver2 = stGamma(nuOver2)

      val normalization = gammaNuPlus1Over2 / (math.sqrt(t.nu * math.Pi) * gammaNuOver2)
      val exponent = math.pow((1 + (x*x)/t.nu),((t.nu+1) * -0.5))

      normalization*exponent
    }
  }

  def stGamma(x:Double): Double = math.sqrt(2*math.Pi/x)*math.pow((x/math.E), x)
}
