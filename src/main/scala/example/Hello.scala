package example

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import tv.kazu.chatwork4s.ChatWorkApiClient
import tv.kazu.chatwork4s.models._
import scala.concurrent.duration.Duration
import scala.util.{ Try, Success, Failure }

object Hello extends Greeting with App {
  val token: String = sys.env("CHATWORK_TOKEN")
  val client = new ChatWorkApiClient(token)
  val f: Future[Room] = client.room(361427)

  f.onSuccess { case room: Room => println(room) }
  f.onFailure { case t: Throwable => println(t.getMessage()) }
  println(f.isCompleted)

  Await.ready(f, Duration.Inf)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
