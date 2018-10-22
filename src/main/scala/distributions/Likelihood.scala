package distributions

import distributions.Distribution._

import cern.jet.math.Bessel

trait Likelihood[T] {
  def getLikelihood(t: T, x: Double): Double
}

object Likelihood {

  implicit object GaussianLikelihood extends Likelihood[Gaussian] {
    def getLikelihood(t: Gaussian, x: Double): Double = {

      val meanCentered = x - 1.0 * t.mean
      val exponent = (meanCentered * meanCentered / t.variance) * -0.5
      val normalization = 1.0 / scala.math.sqrt(2.0 * scala.math.Pi * t.variance)
      normalization * scala.math.exp(exponent)
    }
  }

  implicit object GaussianMixtureLikelihood extends Likelihood[GaussianMixture] {

    def getLikelihood(t: GaussianMixture, x: Double): Double = 1
  }

  implicit object GaussianComponentLikelihood extends Likelihood[GaussianComponent] {

    def getLikelihood(t: GaussianComponent,x: Double): Double = {

      t.weight*calculateLikelihood(t.gaussian,x)
    }
  }

  implicit object vonMisesLikelihood extends Likelihood[VonMises] {

    def getLikelihood(t: VonMises, x: Double): Double = {

      val kappaBessel = Bessel.i0(t.kappa)
      val denom = 2.0 * math.Pi * kappaBessel

      val exponent = t.kappa * (x - t.mu)

      math.exp(exponent) / denom
    }
  }

  implicit object studentTLikelihood extends Likelihood[StudentT] {

    def getLikelihood(t: StudentT, x: Double): Double = {

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
