package org.whsv26.reporter

type EventFieldContext = EventField.type
type OrderFieldContext = OrderField.type
type FieldContext = EventFieldContext|OrderFieldContext

trait DataSource[T <: FieldContext] {
  def sql(): String
  def context(): T
}

enum EventField {
  case EventId
  case OrderId
  case StatusFrom
  case StatusTo
}

enum OrderField {
  case OrderId
  case Status
}

object OrdersSource extends DataSource[OrderField.type] {
  override def context(): OrderFieldContext = OrderField
  override def sql(): String =
    """
      |SELECT
      |    o.order_id,
      |    o.status
      |FROM orders o
      |""".stripMargin
}

object EventsSource extends DataSource[EventField.type] {
  override def context(): EventFieldContext = EventField
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
