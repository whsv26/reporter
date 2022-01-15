package org.whsv26.reporter

import infrastructure.datasource.*
import infrastructure.computation.*
import infrastructure.compiling.*
import domain.*

import org.scalatest.*
import org.scalatest.flatspec.*
import org.scalatest.matchers.*
import scala.collection.mutable.Stack

class GroupingCompilerTest extends AnyFlatSpec with should.Matchers {
  behavior of ("Grouping compiler")

  it should "compile grouping by year" in {
    GroupingCompiler.compile(ByYear, OrderSource()) should be {
      "toStartOfYear(`created_at`)"
    }
    GroupingCompiler.compile(ByYear, EventSource()) should be {
      "toStartOfYear(`order_created_at`)"
    }
  }

  it should "compile grouping by month" in {
    GroupingCompiler.compile(ByMonth, OrderSource()) should be {
      "toStartOfMonth(`created_at`)"
    }
    GroupingCompiler.compile(ByMonth, EventSource()) should be {
      "toStartOfMonth(`order_created_at`)"
    }
  }

  it should "compile grouping by week" in {
    GroupingCompiler.compile(ByWeek, OrderSource()) should be {
      "toStartOfWeek(`created_at`, 3)"
    }
    GroupingCompiler.compile(ByWeek, EventSource()) should be {
      "toStartOfWeek(`order_created_at`, 3)"
    }
  }

  it should "compile grouping by day" in {
    GroupingCompiler.compile(ByDay, OrderSource()) should be {
      "toStartOfDay(`created_at`)"
    }
    GroupingCompiler.compile(ByDay, EventSource()) should be {
      "toStartOfDay(`order_created_at`)"
    }
  }

  it should "compile grouping by hour" in {
    GroupingCompiler.compile(ByHour, OrderSource()) should be {
      "toStartOfHour(`created_at`)"
    }
    GroupingCompiler.compile(ByHour, EventSource()) should be {
      "toStartOfHour(`order_created_at`)"
    }
  }
}
