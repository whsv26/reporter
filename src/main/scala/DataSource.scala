package org.whsv26.reporter

type EventFieldsContext = EventField.type
type OrderFieldContext = OrderField.type
type FieldContext = EventFieldsContext|OrderFieldContext

sealed trait DataSource[T <: FieldContext] {
  def sql(): String
  def context(): T
}

sealed trait ContextualField

enum EventField extends ContextualField {
  case EventId extends EventField
  case OrderId extends EventField
  case StatusFrom extends EventField
  case StatusTo extends EventField
}

enum OrderField extends ContextualField {
  case OrderId extends OrderField
  case Status extends OrderField
}

object OrdersSource extends DataSource[OrderFieldContext] {
  override def context(): OrderFieldContext = OrderField
  override def sql(): String =
    """
      |SELECT
      |    o.order_id,
      |    o.status
      |FROM orders o
      |""".stripMargin
}

object EventsSource extends DataSource[EventFieldsContext] {
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
