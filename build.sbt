import AssemblyKeys._

name := "kollektd"

organization := "com.paulasmuth"

version := "0.0.1"

mainClass in (Compile, run) := Some("Kollekt")

scalaSource in Compile <<= baseDirectory(_ / "src")

scalaVersion := "2.9.1"

assemblySettings

jarName in assembly <<= (version) { v => "kollektd_" + v + ".jar" }

fork in run := true
