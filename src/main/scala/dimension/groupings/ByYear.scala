package org.whsv26.reporter
package dimension.groupings

import datasource.*
import dimension.*
import ImplicitConversions.given
import scala.language.implicitConversions
import Functions.*

given OrderSourceGrouping[ByYear.type] with
  def formula: Formula =
    toStartOfYear(ctx.CreatedAt)

given EventSourceGrouping[ByYear.type] with
  def formula: Formula =
    toStartOfYear(ctx.OrderCreatedAt)
