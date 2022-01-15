package org.whsv26.reporter
package fact.metrics

import AggregateFunctions.*
import OrderStatus.*
import datasource.*
import fact.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInSpamStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Spam)

given EventSourceMetric[OrdersInSpamStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Spam)
