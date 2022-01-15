package org.whsv26.reporter

import AggregateFunctions.*
import compiling.FormulaCompiler
import datasource.*
import dimension.*
import fact.*

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*

import scala.collection.mutable.Stack

class GroupingCompilerTest extends AnyFlatSpec with should.Matchers {
  behavior of ("Grouping compiler")

  it should "compile grouping by year" in {
    compiling.Compiler.compile(ByYear, OrderSource()) should be {
      "toStartOfYear(`created_at`)"
    }
    compiling.Compiler.compile(ByYear, EventSource()) should be {
      "toStartOfYear(`order_created_at`)"
    }
  }

  it should "compile grouping by month" in {
    compiling.Compiler.compile(ByMonth, OrderSource()) should be {
      "toStartOfMonth(`created_at`)"
    }
    compiling.Compiler.compile(ByMonth, EventSource()) should be {
      "toStartOfMonth(`order_created_at`)"
    }
  }

  it should "compile grouping by week" in {
    compiling.Compiler.compile(ByWeek, OrderSource()) should be {
      "toStartOfWeek(`created_at`, 3)"
    }
    compiling.Compiler.compile(ByWeek, EventSource()) should be {
      "toStartOfWeek(`order_created_at`, 3)"
    }
  }

  it should "compile grouping by day" in {
    compiling.Compiler.compile(ByDay, OrderSource()) should be {
      "toStartOfDay(`created_at`)"
    }
    compiling.Compiler.compile(ByDay, EventSource()) should be {
      "toStartOfDay(`order_created_at`)"
    }
  }

  it should "compile grouping by hour" in {
    compiling.Compiler.compile(ByHour, OrderSource()) should be {
      "toStartOfHour(`created_at`)"
    }
    compiling.Compiler.compile(ByHour, EventSource()) should be {
      "toStartOfHour(`order_created_at`)"
    }
  }
}
