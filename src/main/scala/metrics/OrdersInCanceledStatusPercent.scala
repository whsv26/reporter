package org.whsv26.reporter
package metrics

import AggregateFunction.*
import ImplicitConversions.given
import OrderStatus.*

import org.whsv26.reporter.datasource.{EventSourceMetric, OrderSourceMetric}

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInCanceledStatusPercent.type] with
  def formula: Formula =
    OrdersInCanceledStatusQty % OrdersQty

given EventSourceMetric[OrdersInCanceledStatusPercent.type] with
  def formula: Formula =
    OrdersInCanceledStatusQty * 100 / OrdersQty
