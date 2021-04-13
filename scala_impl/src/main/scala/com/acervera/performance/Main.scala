package com.acervera.performance

import better.files._

import scala.collection.immutable

object Main {

  def main(args: Array[String]): Unit = {
    Config(args) match {
      case Some(cfg) =>
        run(
          File(cfg.inputPath),
          immutable.HashMap.empty,
          immutable.HashMap.empty
        )
      case _ =>
    }
  }

  def run(
      inputFile: File,
      nodes: Map[Long, Node],
      ways: Map[Long, Line]
  ): Unit = {
    val updatedLines = inputFile.lineIterator.foldLeft[Map[Long, Line]](ways)((m, json) => {
      val line = Parser.parser(json)
      m + (line.id -> line)
    })

    println("Indexed!")
    println(s"${updatedLines.size}")

  }

}
