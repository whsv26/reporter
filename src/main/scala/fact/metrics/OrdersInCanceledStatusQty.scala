package org.whsv26.reporter
package fact.metrics

import AggregateFunctions.*
import OrderStatus.*
import datasource.*
import fact.*
import ImplicitConversions.given

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInCanceledStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Canceled)

given EventSourceMetric[OrdersInCanceledStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Canceled)
