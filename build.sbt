resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val commonSettings = Seq(
// Refine scalac params from tpolecat
  scalacOptions --= Seq(
    "-Xfatal-warnings"
  )
)

lazy val catsDeps = libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % Version.cats
)

lazy val httpDeps = libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-core"         % Version.http4s,
  "org.http4s" %% "http4s-dsl"          % Version.http4s,
  "org.http4s" %% "http4s-blaze-server" % Version.http4s,
  "org.http4s" %% "http4s-circe"        % Version.http4s
)

lazy val tapirDeps = libraryDependencies ++= Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-core"               % Version.tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-sttp-client"        % Version.tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server"      % Version.tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-http4s"  % Version.tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs"       % Version.tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % Version.tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-json-circe"         % Version.tapir
)

lazy val zioDeps = libraryDependencies ++= Seq(
  "dev.zio" %% "zio"              % Version.zio,
  "dev.zio" %% "zio-test"         % Version.zio % "test",
  "dev.zio" %% "zio-test-sbt"     % Version.zio % "test",
  "dev.zio" %% "zio-interop-cats" % Version.zioInteropCats
)

lazy val root = (project in file("."))
  .settings(
    organization := "Neurodyne",
    name := "tapir-demo",
    version := "0.0.1",
    scalaVersion := "2.13.1",
    maxErrors := 3,
    commonSettings,
    zioDeps,
    httpDeps,
    tapirDeps,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

// Aliases
addCommandAlias("rel", "reload")
addCommandAlias("com", "all compile test:compile it:compile")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")
