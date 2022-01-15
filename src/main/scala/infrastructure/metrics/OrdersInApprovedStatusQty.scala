package org.whsv26.reporter
package infrastructure.metrics

import domain.*
import infrastructure.datasource.{EventSourceMetric, OrderSourceMetric}
import infrastructure.computation.*
import infrastructure.computation.ImplicitConversions.given
import scala.language.implicitConversions
import OrderStatus.*

given OrderSourceMetric[OrdersInApprovedStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Approved)

given EventSourceMetric[OrdersInApprovedStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Approved)