import breeze.linalg._

object LikelihoodFunctions{

  def calculateGaussianDensity(parameters: GaussianParameters,point: DenseVector[Double]): Double = {

    val meanCentered = point + (parameters.mean * -1.0)
    val exponent = (meanCentered.t * inv(parameters.covariance) * meanCentered) * -0.5
    val normalization = 1.0 / scala.math.sqrt(2.0 * scala.math.Pi * det(parameters.covariance))

    return (normalization * scala.math.exp(exponent))
  }

  def calculateGaussianMixtureDensity(mixtureParameters: GaussianMixtureParameters,point: DenseVector[Double]) : Double = {

    val componentDensities = mixtureParameters.componentParams.map(component => LikelihoodFunctions.calculateGaussianDensity(component,point))
    val weightedDensities = (mixtureParameters.weights.zip(componentDensities)).map(weightAndDensity => weightAndDensity._1 * weightAndDensity._2)

    return weightedDensities.sum
  }

  def calculateVonMisesDensity(parameters: VonMisesParameters,point: DenseVector[Double]): Double = {

    return 0.0
  }

  def calculateVonMisesMixtureDensity(parameters: VonMisesMixtureParameters, point: DenseVector[Double]): Double = {

    return 0.0
  }
}
