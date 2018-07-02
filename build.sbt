val breeze = "org.scalanlp" %% "breeze" % "0.13.2"
val scalatic = "org.scalactic" %% "scalactic" % "3.0.5"
val scalatest = "org.scalatest" %% "scalatest" % "3.0.5" % "test"

lazy val root = (project in file("."))
  .settings(
    name := "distributions",
    organization := "us.dac",
    scalaVersion := "2.12.6",
    version := "0.0.1-SNAPSHOT",
    resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",
    libraryDependencies += breeze,
    libraryDependencies += scalatic,
    libraryDependencies += scalatest,
  )

  logBuffered in Test := false
