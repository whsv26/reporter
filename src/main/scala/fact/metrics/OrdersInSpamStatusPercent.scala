package org.whsv26.reporter
package fact.metrics

import AggregateFunctions.*
import OrderStatus.*
import datasource.*
import fact.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInSpamStatusPercent.type] with
  def formula: Formula =
    OrdersInSpamStatusQty % OrdersQty

given EventSourceMetric[OrdersInSpamStatusPercent.type] with
  def formula: Formula =
    OrdersInSpamStatusQty * 100 / OrdersQty
