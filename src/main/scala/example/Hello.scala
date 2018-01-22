package example

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import tv.kazu.chatwork4s.ChatWorkApiClient
import tv.kazu.chatwork4s.models._
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import scala.concurrent.duration.Duration
import scala.util.{ Try, Success, Failure }

object Hello extends Greeting with App {
  val logger = Logger(LoggerFactory.getLogger("logger_name"))
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
