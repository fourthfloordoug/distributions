package distributions

sealed trait Distribution

object Distribution {

    final case class Gaussian(mean: Double, variance: Double) extends Distribution
    final case class GaussianMixture(gaussians: List[Gaussian], weights: List[Double]) extends Distribution
    final case class VonMises(mean: Double, kappa: Double) extends Distribution
    final case class StudentT(t: Double, nu: Int) extends Distribution

    def calculateLikelihood[T: Likelihood](t: T, x: Double): Double = {

      implicitly [Likelihood[T]].getLikelihood(t,x)
    }
}
