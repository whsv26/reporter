package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions
import OrderStatus.*

given OrderSourceMetric[OrdersInSpamStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Spam)

given EventSourceMetric[OrdersInSpamStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Spam)
