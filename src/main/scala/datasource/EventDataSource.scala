package org.whsv26.reporter
package datasource

import fact.Metric
import dimension.Grouping

trait EventSourceMetric[M <: Metric] extends ContextualMetric[M, EventSource] {
  type ContextType = EventField.type
  given EventSource = new EventSource
  final override def ctx: ContextType = EventField
}

trait EventSourceGrouping[G <: Grouping] extends ContextualGrouping[G, EventSource] {
  type ContextType = EventField.type
  final override def ctx: ContextType = EventField
}

enum EventField extends ContextualField {
  case EventId
  case OrderId
  case StatusFrom
  case StatusTo
  case OrderCreatedAt
}

class EventSource extends DataSource {
  override def sql: String =
    """
      |SELECT
      |    e.event_id,
      |    e.order_id,
      |    e.status_from,
      |    e.status_to,
      |    e.order_created_at
      |FROM events e
      |""".stripMargin

}

object EventSource {
  def apply(): EventSource = new EventSource()
}
