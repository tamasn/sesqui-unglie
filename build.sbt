val root = (project in file(".")).settings(
    name := "sesqui-unglie",
    crossScalaVersions := List("2.11.12", "2.12.8"),
    scalacOptions += "-Ypartial-unification"
)