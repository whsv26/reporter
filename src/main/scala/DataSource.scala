package org.whsv26.reporter

type EventFieldContext = EventField.type
type OrderFieldContext = OrderField.type
type FieldContext = EventFieldContext|OrderFieldContext

trait DataSource[T <: FieldContext] {
  def sql(): String
  def context(): T
}

sealed trait ContextualField {
  def snake(): String
}

enum EventField(val fld: String) extends ContextualField {
  case EventId extends EventField("event_id")
  case OrderId extends EventField("order_id")
  case StatusFrom extends EventField("status_from")
  case StatusTo extends EventField("status_to")

  override def snake(): String = fld
}

enum OrderField(val fld: String) extends ContextualField {
  case OrderId extends OrderField("order_id")
  case Status extends OrderField("status")

  override def snake(): String = fld
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
