package org.whsv26.reporter
package datasource

trait OrderSourceMetric[M <: Metric] extends ContextualMetric[M, OrderSource] {
  type ContextType = OrderField.type
  given OrderSource = new OrderSource
  final override def ctx: ContextType = OrderField
}

enum OrderField extends ContextualField {
  case OrderId
  case Status
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