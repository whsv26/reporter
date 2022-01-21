package org.whsv26.reporter
package infrastructure.compiling

import domain.*
import infrastructure.*
import infrastructure.datasource.DataSource

object Compiler:
  def compile(metric: Metric, source: DataSource): String =
    MetricCompiler.compile(metric, source)

  def compile(grouping: Grouping, source: DataSource): String =
    GroupingCompiler.compile(grouping, source)
