lazy val core = (project in file("core"))
  .settings(Settings.shared)
  .settings(name := "sesqui-unglie-core")

lazy val string = (project in file("string"))
  .settings(Settings.shared)
  .settings(name := "sesqui-unglie-string")
  .dependsOn(core)

lazy val root = (project in file("."))
  .settings(Settings.base)
  .settings(name := "sesqui-unglie")
  .aggregate(core, string)
