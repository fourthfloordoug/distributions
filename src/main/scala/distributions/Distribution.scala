package distributions

import scala.concurrent.Future

sealed trait Distribution

object Distribution {

    final case class Gaussian(mean: Double, variance: Double) extends Distribution
    final case class GaussianComponent(gaussian: Gaussian,weight: Double) extends Distribution
    final case class GaussianMixture(components: List[GaussianComponent]) extends Distribution
    final case class VonMises(mu: Double, kappa: Double) extends Distribution
    final case class StudentT(nu: Int) extends Distribution

    def calculateLikelihood[T: Likelihood](t: T, x: Double): Future[Double] = {

      implicitly [Likelihood[T]].getLikelihood(t,x)
    }
}
