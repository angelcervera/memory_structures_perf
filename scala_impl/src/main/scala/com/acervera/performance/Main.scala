package com.acervera.performance

import better.files._

import scala.collection.immutable

object Main {

  def main(args: Array[String]): Unit = {
    Config(args) match {
      case Some(cfg) =>
        run(
          File(cfg.inputPath),
          cfg.nodesIdx.createMap,
          cfg.waysIdx.createMap,
        )
      case _ =>
    }
  }

  def run(
      inputFile: File,
      nodes: collection.Map[Long, Node],
      ways: collection.Map[Long, Line]
  ): Unit = {
    val updatedLines = inputFile.lineIterator.foldLeft[collection.Map[Long, Line]](ways)((m, json) => {
      val line = Parser.parser(json)
      m + (line.id -> line)
    })

    println("Indexed!")
    println(s"${updatedLines.size}")

  }

}
