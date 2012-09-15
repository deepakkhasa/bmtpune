// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.0.2")


resolvers += Resolver.url("xsbt-scalate-generator",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(
    Resolver.ivyStylePatterns)

//addSbtPlugin("com.mojolly.scalate" % "xsbt-scalate-generator" % "0.2.1")

//addSbtPlugin("org.fusesource.scalate" % "sbt-scalate-plugin" % "1.5.2")