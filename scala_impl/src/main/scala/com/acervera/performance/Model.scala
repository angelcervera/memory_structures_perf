package com.acervera.performance

case class Location(latitude: Double, longitude: Double)
case class Node(id: Long, location: Location, tags: Map[String, String])
case class Line(id: Long, nodes: Seq[Node], tags: Map[String, String])
