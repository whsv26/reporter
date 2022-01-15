package org.whsv26.reporter
package metrics

import AggregateFunction.*
import ImplicitConversions.given
import OrderStatus.*

import org.whsv26.reporter.datasource.{EventSourceMetric, OrderSourceMetric}

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInApprovedStatusQty.type] with
  def formula: Formula =
    countIf(ctx.OrderId, ctx.Status === Approved)

given EventSourceMetric[OrdersInApprovedStatusQty.type] with
  def formula: Formula =
    countDistinctIf(ctx.OrderId, ctx.StatusTo === Approved)