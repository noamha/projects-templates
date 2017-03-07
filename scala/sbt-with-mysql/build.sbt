import DatabaseSettings._
import Dependencies._
import PublishSettings._
import com.github.sbtliquibase.SbtLiquibase
import Tasks._

lazy val dummy = (project in file(".")).
  settings(scalikejdbcSettings).
  settings(
    resolvers += Resolver.mavenLocal,
    name := "dummy",
    version := s"0.0.1-${System.currentTimeMillis/1000}",
    organization := "com.acme",
    scalaVersion := "2.11.8",
    scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-language:implicitConversions", "-language:postfixOps"),
    libraryDependencies ++= dependencies,
    libraryDependencies ++= testDependencies,
    parallelExecution in Test := false,
    liquibaseSettings,
    assemblySettings,
    artifactSettings,
    customTasks
  ).enablePlugins(SbtLiquibase)


