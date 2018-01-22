package example

object Hello extends Greeting with App {
  println(greeting)
  println("hogehogehoge")
}

trait Greeting {
  lazy val greeting: String = "hello"
}
