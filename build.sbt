name := "spark_sample"

version := "0.1"

scalaVersion := "2.9.2"

libraryDependencies += "org.spark-project" %% "spark-core" % "0.7.0"

libraryDependencies += "com.stackmob" % "newman_2.9.2" % "0.14.2"

libraryDependencies += "org.json4s" % "json4s-native_2.9.2" % "3.2.5"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.cc/")
