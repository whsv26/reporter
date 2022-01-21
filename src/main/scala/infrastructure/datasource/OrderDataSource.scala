package org.whsv26.reporter
package infrastructure.datasource

import domain.{Metric, Grouping}

trait OrderSourceMetric[M <: Metric] extends ContextualMetric[M, OrderSource]:
  type ContextType = OrderField.type
  given OrderSource = new OrderSource
  final override def ctx: ContextType = OrderField

trait OrderSourceGrouping[G <: Grouping] extends ContextualGrouping[G, OrderSource]:
  type ContextType = OrderField.type
  final override def ctx: ContextType = OrderField

enum OrderField extends ContextualField:
  case OrderId
  case Status
  case CreatedAt

class OrderSource extends DataSource:
  def sql: String =
    """
      |SELECT
      |    o.order_id,
      |    o.status,
      |    o.created_at
      |FROM orders o
      |""".stripMargin

object OrderSource:
  def apply(): OrderSource = new OrderSource()
