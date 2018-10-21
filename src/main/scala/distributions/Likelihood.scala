package distributions

import distributions.Distribution._

trait Likelihood[T] {
  def getLikelihood(t: T, x: Double): Double
}

object Likelihood {

  implicit object GaussianLikelihood extends Likelihood[Gaussian] {
    def getLikelihood(t: Gaussian, x: Double) = 0
  }

  implicit object GaussianMixtureLikelihood extends Likelihood[GaussianMixture] {

    def getLikelihood(t: GaussianMixture, x: Double) = 1
  }

  implicit object GaussianComponentLikelihood extends Likelihood[GaussianComponent] {

    def getLikelihood(t: GaussianComponent,x: Double) = 6
  }

  implicit object vonMisesLikelihood extends Likelihood[VonMises] {

    def getLikelihood(t: VonMises, x: Double) = 2
  }

  implicit object studentTLikelihood extends Likelihood[StudentT] {

    def getLikelihood(t: StudentT, x: Double) = 3
  }
}
