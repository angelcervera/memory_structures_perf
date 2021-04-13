package com.acervera.performance

import scopt.OParser

sealed trait IndexType

object IndexType {
  def builder(txt: String): IndexType =
    txt match {
      case "hashmap" => HashMapType
      case "btree"   => BTMapType
      case _         => throw new IllegalArgumentException(s"[$txt] is not supported")
    }
}

case object HashMapType extends IndexType
case object BTMapType extends IndexType

case class Config(
    nodesIdx: IndexType = HashMapType,
    waysIdx: IndexType = HashMapType,
    inputPath: String = ""
)

object Config {

  def apply(args: Array[String]): Option[Config] = {

    val builder = OParser.builder[Config]
    import builder._

    def addNodesIdx =
      opt[String]("nodes")
        .valueName("INDEX_TYPE")
        .action((x, c) => c.copy(nodesIdx = IndexType.builder(x)))
        .text("Type of index used to store nodes")

    def addWaysIdx =
      opt[String]("ways")
        .valueName("INDEX_TYPE")
        .action((x, c) => c.copy(waysIdx = IndexType.builder(x)))
        .text("Type of index used to store ways")

    def addInput =
      arg[String]("<file>")
        .action((x, c) => c.copy(inputPath = x))
        .text("Input file to use")

    val parser = OParser.sequence(
      programName("scala-performance-<version>.jar"),
      head("Scala performance using HashMap and BTreeMap", "0.0.1-SNAPSHOT"),
      addNodesIdx,
      addWaysIdx,
      addInput
    )

    OParser.parse(parser, args, Config())
  }
}
