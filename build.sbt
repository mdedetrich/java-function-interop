ThisBuild / name := "java-function-interop"

ThisBuild / crossScalaVersions := Seq("3.2.0", "2.13.9", "2.12.17", "2.11.12", "2.10.7")
ThisBuild / scalaVersion := crossScalaVersions.value.head

// We use <epoch>.<major>.<minor> like 99% of Scala libraries.
// Versions are binary compatible within x.y.* but not within x.*.*
ThisBuild / versionScheme := Some("pvp")
ThisBuild / versionPolicyIntention := Compatibility.None // 3.0.0

def warningFlags(scalaVersion: String): Seq[String] = CrossVersion.partialVersion(scalaVersion) match {
  case Some((2, n)) if n >= 12 =>
    Seq("-Wconf:msg=@SerialVersionUID has no effect on traits:s")
  case Some((3, _)) =>
    Seq("-Wconf:msg=@SerialVersionUID does nothing on a trait:s")
  case _ =>
    Nil
}

lazy val javaFunctionInteropt = project
  .in(file("."))
  .settings(ScalaModulePlugin.scalaModuleSettings)
  .settings(ScalaModulePlugin.scalaModuleOsgiSettings)
  .settings(
    name := "java-function-interopt",
    scalaModuleAutomaticModuleName := Some("scala.interopt.java"),
    OsgiKeys.exportPackage := Seq(s"scala.swing.*;version=${version.value}"),
    scalacOptions ++= warningFlags(scalaVersion.value)
  )
  .enablePlugins(spray.boilerplate.BoilerplatePlugin, JavaFormatterPlugin)
