package org.whsv26.reporter

import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    for {
      status <- IO.pure(ExitCode.Success)
    } yield status
  }
}
