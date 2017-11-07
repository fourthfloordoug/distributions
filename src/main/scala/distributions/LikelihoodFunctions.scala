import breeze.linalg._

object LikelihoodFunctions{

  def calculateGaussianDensity(parameters: GaussianParameters,point: DenseVector[Double]): Double = {

    val meanCentered = point + (parameters.mean * -1.0)
    val exponent = (meanCentered.t * inv(parameters.covariance) * meanCentered) * -0.5
    val normalization = 1.0 / scala.math.sqrt(2.0 * scala.math.Pi * det(parameters.covariance))

    return (normalization * scala.math.exp(exponent))
  }

  def calculateGaussianMixtureDensity(parameters: GaussianMixtureParameters,point: DenseVector[Double]) : Double = {

    // val componentLikelihoods = parameters.componentParams.map(component => calcualteGaussianDensity(component,point))
    // val weightedLikelihoods = (parameters.weights, componentLikelihoods).zipped map (_ + _)
    // return weightedLikelihoods.sum
    return 0.0
  }

  def calculateVonMisesDensity(parameters: VonMisesParameters,point: DenseVector[Double]): Double = {

    return 0.0
  }

  def calculateVonMisesMixtureDensity(parameters: VonMisesMixtureParameters, point: DenseVector[Double]): Double = {

    return 0.0
  }
}
