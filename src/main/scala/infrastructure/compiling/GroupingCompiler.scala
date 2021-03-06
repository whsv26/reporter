package org.whsv26.reporter
package infrastructure.compiling

import domain.*
import infrastructure.computation.*
import infrastructure.datasource.*
import infrastructure.groupings.given

object GroupingCompiler:
  def compile(grouping: Grouping, source: DataSource): String = (grouping, source) match
    case (g: ByYear.type, s: OrderSource) => compile(formula(g, s))
    case (g: ByYear.type, s: EventSource) => compile(formula(g, s))
    case (g: ByMonth.type, s: OrderSource) => compile(formula(g, s))
    case (g: ByMonth.type, s: EventSource) => compile(formula(g, s))
    case (g: ByWeek.type, s: OrderSource) => compile(formula(g, s))
    case (g: ByWeek.type, s: EventSource) => compile(formula(g, s))
    case (g: ByDay.type, s: OrderSource) => compile(formula(g, s))
    case (g: ByDay.type, s: EventSource) => compile(formula(g, s))
    case (g: ByHour.type, s: OrderSource) => compile(formula(g, s))
    case (g: ByHour.type, s: EventSource) => compile(formula(g, s))

  private def formula[G <: Grouping, S <: DataSource](
    grouping: G,
    source: S
  )(using
    cg: ContextualGrouping[G, S]
  ): Formula = cg.formula

  private def compile(formula: Formula): String =
    FormulaCompiler.compile(formula)

