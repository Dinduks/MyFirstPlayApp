import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "MyFirstPlayApp"
    val appVersion      = "0.0.1-SNAPSHOT"

    val appDependencies = Seq(
      "postgresql" % "postgresql" % "8.4-702.jdbc4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    )

}
