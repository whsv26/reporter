package org.whsv26.reporter
package infrastructure.datasource

import infrastructure.computation.*
import domain.{Metric, Grouping}

trait ContextualField

trait ContextualMetricFormulas extends FunctionFormulas
  with AggregateFunctionFormulas

trait ContextualMetric[M <: Metric, S <: DataSource] extends ContextualMetricFormulas:
  type ContextType
  def ctx: ContextType
  def formula: Formula


trait ContextualGrouping[G <: Grouping, S <: DataSource] extends FunctionFormulas:
  type ContextType
  def ctx: ContextType
  def formula: Formula

trait DataSource:
  def sql: String
