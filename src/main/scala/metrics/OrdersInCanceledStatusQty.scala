package org.whsv26.reporter
package metrics

import Aggregate.*
import ImplicitConversions.given
import MetricName.*
import OrderStatus.*

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInCanceledStatusQty.type] with {
  def formula: Formula = {
    countIf(ctx.OrderId, ctx.Status === Canceled)
  }
}

given EventSourceMetric[OrdersInCanceledStatusQty.type] with {
  def formula: Formula = {
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Canceled)
  }
}