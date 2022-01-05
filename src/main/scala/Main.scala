package org.whsv26.reporter

import cats.effect.{ExitCode, IO, IOApp}
import metrics.given
import MetricName.*

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- IO.println(Compiler.compile(OrdersInApprovedStatusPercent, new OrderSource))
      _ <- IO.println(Compiler.compile[OrdersQty.type, OrderSource])
      status <- IO.pure(ExitCode.Success)
    } yield status
  }
}
