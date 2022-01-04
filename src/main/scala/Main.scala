package org.whsv26.reporter

import cats.effect.{ExitCode, IO, IOApp}
import MetricImplementations.given

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    val formula = if Math.random() > 0.5
      then MetricEnum.OrdersQty.formula(OrdersSource)
      else MetricEnum.OrdersQty.formula(EventsSource)

    for {
      _ <- IO.println(formula)
      status <- IO.pure(ExitCode.Success)
    } yield status
  }
}
