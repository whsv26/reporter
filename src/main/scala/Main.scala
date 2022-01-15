package org.whsv26.reporter

import datasource.{EventSource, OrderSource}

import cats.effect.{ExitCode, IO, IOApp}
import org.whsv26.reporter.fact.{OrdersInApprovedStatusPercent, OrdersQty}

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- IO.println(Compiler.compile(OrdersInApprovedStatusPercent, new OrderSource))
      _ <- IO.println(Compiler.compile(OrdersQty, new EventSource))
      status <- IO.pure(ExitCode.Success)
    } yield status
  }
}
