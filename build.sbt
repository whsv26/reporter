ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.0.2"

lazy val root = (project in file("."))
  .settings(
    name := "reporter",
    idePackagePrefix := Some("org.whsv26.reporter")
  )

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.7.0",
  "org.typelevel" %% "cats-kernel" % "2.7.0",
  "org.typelevel" %% "cats-free" % "2.7.0",
  "org.typelevel" %% "cats-effect" % "3.3.0",
)

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.10",
  "org.scalatest" %% "scalatest" % "3.2.10" % "test"
)
