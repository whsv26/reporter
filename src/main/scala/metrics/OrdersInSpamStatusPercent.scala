package org.whsv26.reporter
package metrics

import AggregateFunction.*
import ImplicitConversions.given
import OrderStatus.*

import org.whsv26.reporter.datasource.{EventSourceMetric, OrderSourceMetric}

import scala.language.implicitConversions

given OrderSourceMetric[OrdersInSpamStatusPercent.type] with
  def formula: Formula =
    OrdersInSpamStatusQty % OrdersQty

given EventSourceMetric[OrdersInSpamStatusPercent.type] with
  def formula: Formula =
    OrdersInSpamStatusQty * 100 / OrdersQty
