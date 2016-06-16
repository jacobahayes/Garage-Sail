name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.feth" %% "play-authenticate" % "0.7.1",
  "mysql" % "mysql-connector-java" % "5.1.39",
  javaJdbc,
  cache,
  "org.avaje.ebeanorm" % "avaje-ebeanorm" % "4.6.2",
  javaWs
)
