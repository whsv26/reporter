package org.whsv26.reporter

import cats.effect.{ExitCode, IO, IOApp}
import metrics.given
import MetricName.*

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val form = if Math.random() > 0.5
      then OrdersQty.formula[OrderSource]
      else OrdersQty.formula[EventSource]

//    compileOrderSource(OrdersQty)

    for {
      _ <- IO.println(form)
      status <- IO.pure(ExitCode.Success)
    } yield status
  }

//  def compileOrderSource[M <: MetricName](m: M)(using ContextualMetric[M, OrderFieldContext]): String = {
//    compile(m, OrderSource)
//  }
//
//  def compileEventSource[M <: MetricName](m: M)(using ContextualMetric[M, EventFieldsContext]): String = {
//    compile(m, EventSource)
//  }
//
//  def compile[M <: MetricName, C <: FieldContext, S <: DataSource[C]](m: M, s: S)(using cm: ContextualMetric[M, C]): String = {
//    Compiler.compile(cm.formula)
//  }
}
