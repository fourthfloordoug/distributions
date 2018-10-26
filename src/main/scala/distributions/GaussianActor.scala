package distributions

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

import distributions.Distribution._

case class ProcessGaussianMsg(component: GaussianComponent, x: Double)
case class GaussianProcessedMsg(likelihood: Double)

/* class GaussianActor extends Actor {

  def receive = {

    case ProcessGaussianMsg(component, x) => {

      val likelihood = component.weight * calculateLikelihood(component.gaussian,x)
      sender ! GaussianProcessedMsg(likelihood)
    }
    case _ => println("Unrecognized message");
  }
} */
