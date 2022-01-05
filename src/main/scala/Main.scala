package org.whsv26.reporter

import cats.effect.{ExitCode, IO, IOApp}
import metrics.given

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val formula = if Math.random() > 0.5
      then MetricName.OrdersQty.formula(OrderSource)
      else MetricName.OrdersQty.formula(EventSource)

    for {
      _ <- IO.println(formula)
      status <- IO.pure(ExitCode.Success)
    } yield status
  }
}
