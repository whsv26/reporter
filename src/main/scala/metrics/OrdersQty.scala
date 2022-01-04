package org.whsv26.reporter
package metrics

import Aggregate.*
import MetricName.*
import ImplicitConversions.given
import scala.language.implicitConversions

given ContextualMetric[OrdersQty.type, OrderField.type] with {
  def formula(ctx: OrderField.type): Formula = {
    count(ctx.OrderId)
  }
}

given ContextualMetric[OrdersQty.type, EventField.type] with {
  def formula(ctx: EventField.type): Formula = {
    countDistinct(ctx.OrderId)
  }
}
