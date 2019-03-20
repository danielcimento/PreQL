name := """sql_server"""
organization := "co.danielcimento"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
libraryDependencies += "com.lihaoyi" %% "fastparse" % "2.1.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "co.danielcimento.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "co.danielcimento.binders._"
