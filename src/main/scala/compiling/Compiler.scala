package org.whsv26.reporter
package compiling

import Formulas.*
import Formulas.AggregateFunctions.*
import Formulas.Arithmetics.*
import Formulas.Predicates.*
import datasource.*
import fact.*
import dimension.*

object Compiler {
  def compile(metric: Metric, source: DataSource): String =
    MetricCompiler.compile(metric, source)

  def compile(grouping: Grouping, source: DataSource): String =
    GroupingCompiler.compile(grouping, source)
}
