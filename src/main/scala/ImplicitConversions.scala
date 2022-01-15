package org.whsv26.reporter

import Formulas.*

object ImplicitConversions {
  given Conversion[ContextualField, Field] = (cf: ContextualField) => Field(cf.toString)
  given Conversion[Boolean|String|Int|Float|OrderStatus, Value] = Value(_)
  given [M <: MetricName, S <: DataSource](using
    src: S,
    cm: ContextualMetric[M, S]
  ): Conversion[M, Formula] with {
    override def apply(name: M): Formula = cm.formula
  }
}

