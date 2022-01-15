package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions
import OrderStatus.*

given OrderSourceMetric[OrdersInCanceledStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Canceled)

given EventSourceMetric[OrdersInCanceledStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Canceled)
