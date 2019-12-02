name := "adventOfCode19"

version := "1.2.7"

scalaVersion := "2.13.1"

libraryDependencies ++= cats ++ scalaTest

lazy val cats = Seq("org.typelevel" %% "cats-effect" % "2.0.0")

lazy val scalaTest = Seq(
	"org.scalatest" %% "scalatest" % "3.0.8" % Test
)