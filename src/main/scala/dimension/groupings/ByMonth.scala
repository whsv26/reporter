package org.whsv26.reporter
package dimension.groupings

import Functions.*
import ImplicitConversions.given
import datasource.*
import dimension.*

import scala.language.implicitConversions

given OrderSourceGrouping[ByMonth.type] with
  def formula: Formula =
    toStartOfMonth(ctx.CreatedAt)

given EventSourceGrouping[ByMonth.type] with
  def formula: Formula =
    toStartOfMonth(ctx.OrderCreatedAt)
