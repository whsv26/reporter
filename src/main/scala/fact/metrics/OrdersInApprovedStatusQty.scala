package org.whsv26.reporter
package fact.metrics

import AggregateFunction.*
import OrderStatus.*
import datasource.*
import fact.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInApprovedStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Approved)

given EventSourceMetric[OrdersInApprovedStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Approved)