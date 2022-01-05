package org.whsv26.reporter
package metrics

import Aggregate.*
import ImplicitConversions.given
import MetricName.*
import OrderStatus.*

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInSpamStatusQty.type] with {
  def formula: Formula = {
    countIf(ctx.OrderId, ctx.Status === Spam)
  }
}

given EventSourceMetric[OrdersInSpamStatusQty.type] with {
  def formula: Formula = {
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Spam)
  }
}