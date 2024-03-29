import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "appment"
    val appVersion      = "1.0"

    val ssDependencies = Seq(
      // Add your project dependencies here,
      "com.typesafe" %% "play-plugins-util" % "2.0.1",
      "org.mindrot" % "jbcrypt" % "0.3m",
      "org.fusesource.scalate" % "sbt-scalate-plugin" % "1.5.3",
      "org.fusesource.scalate" % "scalate-util" % "1.5.3",
      "org.fusesource.scalate" % "scalate-core" % "1.5.3",
      "mysql" % "mysql-connector-java" % "5.1.18"     ,
    	"org.apache.commons" % "commons-email" % "1.2" ,
    	"org.scalaj" %% "scalaj-time" % "0.6",
    	"com.opentok.api" % "opentok-java-sdk" % "0.91.57"
    )
 
    val secureSocial = PlayProject(
    	appName + "-securesocial", appVersion, ssDependencies, mainLang = SCALA, path = file("modules/securesocial")
    ).settings(
      resolvers ++= Seq(
        "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
      )
    )

    val appDependencies = Seq()
    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    ).dependsOn(secureSocial).aggregate(secureSocial)

	//val libraryDependencies += "org.fusesource.scalate" % "sbt-scalate-plugin" % "1.5.3",
}
