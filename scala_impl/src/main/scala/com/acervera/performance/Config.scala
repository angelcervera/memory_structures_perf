package com.acervera.performance

import scopt.OParser

import scala.collection.{immutable, mutable}

sealed trait IndexType {
    def createMap[T]: collection.Map[Long, T] = this match {
      case IHashMapType => immutable.HashMap.empty
      case IBTMapType => immutable.TreeMap.empty
      case MHashMapType => mutable.HashMap.empty
      case MBTMapType => mutable.TreeMap.empty
    }
}

object IndexType {

  private val values = Seq("i_hashmap", "i_treemap", "m_hashmap", "m_treemap")

  def builder(txt: String): IndexType =
    txt match {
      case "i_hashmap" => IHashMapType
      case "i_treemap"   => IBTMapType
      case "m_hashmap" => MHashMapType
      case "m_treemap"  => MBTMapType
      case _         => throw new IllegalArgumentException(s"[$txt] is not supported. Valid values are [${values.mkString(",")}]")
    }
}

case object IHashMapType extends IndexType

case object IBTMapType extends IndexType

case object MHashMapType extends IndexType

case object MBTMapType extends IndexType

case class Config(
                   nodesIdx: IndexType = IHashMapType,
                   waysIdx: IndexType = IHashMapType,
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
