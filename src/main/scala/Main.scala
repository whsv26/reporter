package org.whsv26.reporter

import domain.*
import infrastructure.*
import infrastructure.datasource.*
import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp:
  def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO.println(compiling.Compiler.compile(OrdersInApprovedStatusPercent, new OrderSource))
      _ <- IO.println(compiling.Compiler.compile(OrdersQty, new EventSource))
      status <- IO.pure(ExitCode.Success)
    } yield status
