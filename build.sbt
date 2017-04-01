
name := "javatagsconverter"

version := "1.0.0-SNAPSHOT"
 
scalaVersion := "2.11.8"

resolvers += Resolver.mavenLocal

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"

libraryDependencies += "org.jsoup" % "jsoup" % "1.10.2"

libraryDependencies += "com.github.manliogit" % "javatags" % "0.4.2"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

enablePlugins(JettyPlugin)

