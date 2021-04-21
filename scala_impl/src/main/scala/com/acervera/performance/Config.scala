package com.acervera.performance

import scopt.OParser

import scala.collection.{immutable, mutable}

sealed trait IndexType

sealed trait Immutable {
  def createMap[T]: immutable.Map[Long, T] =
    this match {
      case IHashMapType => immutable.HashMap.empty
      case IBTMapType   => immutable.TreeMap.empty
    }
}

sealed trait Mutable {
  def createMap[T]: mutable.Map[Long, T] =
    this match {
      case MHashMapType => mutable.HashMap.empty
      case MBTMapType   => mutable.TreeMap.empty
    }
}

object IndexType {

  private val values = Seq("i_hashmap", "i_treemap", "m_hashmap", "m_treemap")

  def builder(txt: String): IndexType =
    txt match {
      case "i_hashmap" => IHashMapType
      case "i_treemap" => IBTMapType
      case "m_hashmap" => MHashMapType
      case "m_treemap" => MBTMapType
      case _ =>
        throw new IllegalArgumentException(
          s"[$txt] is not supported. Valid values are [${values.mkString(",")}]"
        )
    }
}

case object IHashMapType extends IndexType with Immutable

case object IBTMapType extends IndexType with Immutable

case object MHashMapType extends IndexType with Mutable

case object MBTMapType extends IndexType with Mutable

case class Config(
    waysIdx: IndexType = IHashMapType,
    inputPath: String = ""
)

object Config {

  def apply(args: Array[String]): Option[Config] = {

    val builder = OParser.builder[Config]
    import builder._

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
      addWaysIdx,
      addInput
    )

    OParser.parse(parser, args, Config())
  }
}
