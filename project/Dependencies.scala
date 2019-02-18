import sbt._

object Versions {
    lazy val scala = "2.12.8"

    lazy val cats = "1.6.0"
    lazy val scalaCheck = "1.14.0"
    lazy val scalaTest = "3.0.5"
}

object Dependencies {
    lazy val cats = Seq(
        "org.typelevel" %% "cats-core",
        "org.typelevel" %% "cats-macros",
        "org.typelevel" %% "cats-kernel"
    ).map(_ % Versions.cats)

    lazy val scalaCheck = Seq(
        "org.scalacheck" %% "scalacheck"
    ).map(_ % Versions.scalaCheck % Test)
    lazy val scalaTest = Seq(
        "org.scalactic" %% "scalactic",
        "org.scalatest" %% "scalatest"
    ).map(_ % Versions.scalaTest % Test)
}