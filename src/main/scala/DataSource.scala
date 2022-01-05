package org.whsv26.reporter

type EventFieldsContext = EventField.type
type OrderFieldContext = OrderField.type
type FieldContext = EventFieldsContext|OrderFieldContext

sealed trait ContextualField
trait ContextualMetric[M <: MetricName, S <: DataSource] {
  type ContextType
  def ctx: ContextType
  def formula: Formula
}

trait OrderSourceMetric[M <: MetricName] extends ContextualMetric[M, OrderSource] {
  type ContextType = OrderField.type
  given OrderSource = new OrderSource
  final override def ctx: ContextType = OrderField
}
trait EventSourceMetric[M <: MetricName] extends ContextualMetric[M, EventSource] {
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

enum OrderField extends ContextualField {
  case OrderId
  case Status
}

sealed trait DataSource {
  def sql: String
}

class OrderSource extends DataSource {
  override def sql: String =
    """
      |SELECT
      |    o.order_id,
      |    o.status
      |FROM orders o
      |""".stripMargin
}
object OrderSource {
  def apply(): OrderSource = new OrderSource()
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
