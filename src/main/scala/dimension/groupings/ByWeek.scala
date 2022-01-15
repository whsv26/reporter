package org.whsv26.reporter
package dimension.groupings

import Functions.*
import ImplicitConversions.given
import datasource.*
import dimension.*

import scala.language.implicitConversions

given OrderSourceGrouping[ByWeek.type] with
  def formula: Formula =
    toStartOfWeek(ctx.CreatedAt)

given EventSourceGrouping[ByWeek.type] with
  def formula: Formula =
    toStartOfWeek(ctx.OrderCreatedAt)
