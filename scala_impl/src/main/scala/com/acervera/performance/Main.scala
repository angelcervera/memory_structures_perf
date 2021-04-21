package com.acervera.performance

import better.files._

import scala.collection.{immutable, mutable}

object Main {

  def main(args: Array[String]): Unit = {
    Config(args) match {
      case Some(cfg) =>
        val m = cfg.waysIdx match {
          case m: Mutable =>
            run_mutable(
              File(cfg.inputPath),
              m.createMap
            )
          case i: Immutable =>
            run_immutable(
              File(cfg.inputPath),
              i.createMap
            )
        }

        println("Indexed!")
        println(s"${m.size}")
      case _ =>
    }
  }

  def run_immutable(
      inputFile: File,
      ways: immutable.Map[Long, Line]
  ): collection.Map[Long, Line] =
    inputFile.lineIterator.foldLeft[collection.Map[Long, Line]](ways)(
      (m, json) => {
        val line = Parser.parser(json)
        m + (line.id -> line)
      }
    )

  def run_mutable(
      inputFile: File,
      ways: mutable.Map[Long, Line]
  ): collection.Map[Long, Line] = {
    inputFile.lineIterator.foreach { json =>
      val line = Parser.parser(json)
      ways.put(line.id, line)
    }
    ways
  }

}
