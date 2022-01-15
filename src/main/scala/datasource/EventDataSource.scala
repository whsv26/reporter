package org.whsv26.reporter
package datasource

import fact.Metric

trait EventSourceMetric[M <: Metric] extends ContextualMetric[M, EventSource] {
  type ContextType = EventField.type
  given EventSource = new EventSource
  final override def ctx: ContextType = EventField
}

enum EventField extends ContextualField {
  case EventId
  case OrderId
  case StatusFrom
  case StatusTo
}

class EventSource extends DataSource {
  override def sql: String =
    """
      |SELECT
      |    e.event_id,
      |    e.order_id,
      |    e.status_from,
      |    e.status_to
      |FROM events e
      |""".stripMargin

}

object EventSource {
  def apply(): EventSource = new EventSource()
}
