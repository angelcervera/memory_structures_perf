
version := "0.0.1-SNAPSHOT"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.acervera.performance",
      scalaVersion := "2.13.5"
    )),
    name := "scala-performance",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.0.1",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.3",
      "com.github.pathikrit" %% "better-files" % "3.9.1",
      "org.scalatest" %% "scalatest" % "3.2.2" % Test
    )
  )
  .enablePlugins(JavaAppPackaging)


