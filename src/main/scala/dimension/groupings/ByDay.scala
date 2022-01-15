package org.whsv26.reporter
package dimension.groupings

import Functions.*
import ImplicitConversions.given
import datasource.*
import dimension.*

import scala.language.implicitConversions

given OrderSourceGrouping[ByDay.type] with
  def formula: Formula =
    toStartOfDay(ctx.CreatedAt)

given EventSourceGrouping[ByDay.type] with
  def formula: Formula =
    toStartOfDay(ctx.OrderCreatedAt)
