import Dependencies._

resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "tv.kazu" %% "chatwork4s" % "0.2.5.2",
    )
  )
