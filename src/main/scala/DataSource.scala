package org.whsv26.reporter

type EventFieldsContext = EventField.type
type OrderFieldContext = OrderField.type
type FieldContext = EventFieldsContext|OrderFieldContext

sealed trait DataSource[T <: FieldContext] {
  def sql(): String
  def context(): T
}

sealed trait ContextualField
trait ContextualMetric[M <: MetricName, C <: FieldContext] {
  def ctx: C
  def formula: Formula
}

trait OrderSourceMetric[M <: MetricName] extends ContextualMetric[M, OrderFieldContext] {
  given DataSource[OrderField.type] = OrderSource
  def ctx: OrderFieldContext = OrderField
}
trait EventSourceMetric[M <: MetricName] extends ContextualMetric[M, EventFieldsContext] {
  given DataSource[EventField.type] = EventSource
  def ctx: EventFieldsContext = EventField
}

enum EventField extends ContextualField {
  case EventId
  case OrderId
  case StatusFrom
  case StatusTo
}

enum OrderField extends ContextualField {
  case OrderId
  case Status
}

object OrderSource extends DataSource[OrderFieldContext] {
  override def context(): OrderFieldContext = OrderField
  override def sql(): String =
    """
      |SELECT
      |    o.order_id,
      |    o.status
      |FROM orders o
      |""".stripMargin
}

object EventSource extends DataSource[EventFieldsContext] {
  override def context(): EventFieldsContext = EventField
  override def sql(): String =
    """
      |SELECT
      |    e.event_id,
      |    e.order_id,
      |    e.status_from,
      |    e.status_to
      |FROM events e
      |""".stripMargin

}
