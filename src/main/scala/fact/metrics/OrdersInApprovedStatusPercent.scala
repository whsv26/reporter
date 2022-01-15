package org.whsv26.reporter
package fact.metrics

import AggregateFunctions.*
import OrderStatus.*
import datasource.*
import fact.*
import ImplicitConversions.given
import scala.language.implicitConversions

given OrderSourceMetric[OrdersInApprovedStatusPercent.type] with
  def formula: Formula =
    OrdersInApprovedStatusQty % OrdersQty

given EventSourceMetric[OrdersInApprovedStatusPercent.type] with
  def formula: Formula =
    OrdersInApprovedStatusQty * 100 / OrdersQty