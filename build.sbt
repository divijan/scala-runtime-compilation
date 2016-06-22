name := "scala-runtime-compilation"

version := "1.0"

scalaVersion := "2.11.8"

val sbtcp = taskKey[Unit]("sbt-classpath")

sbtcp := {
  val files: Seq[File] = (fullClasspath in Compile).value.files
  val sbtClasspath : String = files.map(_.getAbsolutePath).mkString(":")
  println("Set SBT classpath to 'sbt-classpath' environment variable")
  System.setProperty("sbt-classpath", sbtClasspath)
}

compile <<= (compile in Compile).dependsOn(sbtcp)
run <<= (run in Runtime).dependsOn(sbtcp)

libraryDependencies ++= Seq(
  "org.clapper" % "grizzled-slf4j_2.11" % "1.0.2",
  "org.scala-lang" % "scala-compiler" % "2.11.8",
  "org.specs2" %% "specs2-core" % "3.8.3" % "test"
)
