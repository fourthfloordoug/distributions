package distributions

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props
import akka.event.Logging

import distributions.Distribution.GaussianMixture
import distributions.Distribution.Gaussian
import distributions.Distribution.GaussianComponent

case class StartProcessGMMMsg()

/*class GMMLikelihoodActor(gmm: GaussianMixture, x: Double) extends Actor {

  private var running = false
  private var totalLikelihood = 0.0
  private var compsProcessed = 0
  private var totalComps = 0
  private var distributionSender: Option[ActorRef] = None

  def receive = {

    case StartProcessGMMMsg() => {

      if(running) {
        println("Warning: duplicate start message")
      } else {
        running = true
        distributionSender = Some(sender)
        gmm.components.foreach { component =>
          context.actorOf(Props[GaussianActor]) ! ProcessGaussianMsg(component, x)
          totalComps += 1 }
      }
    }
    case GaussianProcessedMsg(componentLikelihood) => {

      totalLikelihood += componentLikelihood
      compsProcessed += 1
      if (compsProcessed == totalComps) {
        distributionSender.map(_ ! totalLikelihood)
      }
    }
    case _ => println("msg not recognized")


  }
} */
