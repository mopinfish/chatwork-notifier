package example

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import tv.kazu.chatwork4s.ChatWorkApiClient
import tv.kazu.chatwork4s.ChatWorkApiResponse
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

  val f2: Future[ChatWorkApiResponse] = client.post("/rooms/361427/messages", Seq(("body", "hoge")))

  f2.onSuccess { case res: ChatWorkApiResponse => println(res) }
  f2.onFailure { case t: Throwable => println(t.getMessage()) }
  println(f2.isCompleted)

  Await.ready(f2, Duration.Inf)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
