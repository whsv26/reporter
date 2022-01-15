package org.whsv26.reporter
package dimension.groupings

import Functions.*
import ImplicitConversions.given
import datasource.*
import dimension.*

import scala.language.implicitConversions

given OrderSourceGrouping[ByHour.type] with
  def formula: Formula =
    toStartOfHour(ctx.CreatedAt)

given EventSourceGrouping[ByHour.type] with
  def formula: Formula =
    toStartOfHour(ctx.OrderCreatedAt)
