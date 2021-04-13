package com.acervera.performance

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.{
  DefaultScalaModule,
  ScalaObjectMapper
}

case class JsonNode(
    id: Long,
    latitude: Double,
    longitude: Double,
    tags: Map[String, String]
)

case class JsonLine(
    id: Long,
    nodes: Seq[JsonNode],
    tags: Map[String, String]
)

object Parser {
  private val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)

  def parser(line: String): Line = {
    val des = mapper.readValue[JsonLine](line)

    Line(
      id = des.id,
      tags = des.tags,
      nodes = des.nodes.map(json =>
        Node(
          id = json.id,
          tags = json.tags,
          location =
            Location(latitude = json.latitude, longitude = json.longitude)
        )
      )
    )
  }
}
