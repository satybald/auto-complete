name := "Auto Complete"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies ++= {
  Seq(
    "com.github.scopt" %% "scopt" % "3.7.0",
    "org.scalatest" %% "scalatest" % "3.0.1" % Test
  )
}
