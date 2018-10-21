package distributions

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

import distributions.Distribution.GaussianMixture
import distributions.Distribution.Gaussian
import distributions.Distribution.GaussianComponent

case class StartProcessGMMMsg()

class GMMLikelihoodActor(gmm: GaussianMixture, x: Double) extends Actor {

  private var running = false
  private var totalLikelihood = 0
  private var compsProcessed = 0
  private var totalComps = 0
  private var distributionSender: Option[ActorRef] = None

  def receive = {

    case StartProcessGMMMsg() => {
      if(running) {
        println("Warning: duplicate start message")
      } else {
        running = true
        distSender = Some(sender)
        GaussianMixture.components.foreach {
          context.actorOf(Pops[GaussianActor]) ! ProcessGaussianMsg(gmm, x)
          totalComps += 1 }
      }
    }
    case GaussianProcessedMsg(componentLikelihood) => {

      totalLikelihood += GaussianComponentLikelihood
      compsProcessed += 1
      if (compsProcessed == totalComps) {
        distSender.map(_ ! result)
      }
    }
    case _ => println("msg not recognized")


  }
}
