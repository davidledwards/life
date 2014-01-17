package com.loopfor.life

import com.loopfor.scalop._
import scala.annotation.tailrec

object Life {
  def main(args: Array[String]): Unit = {
    import Console.err
    try {
      run(args)
      sys exit 0
    } catch {
      case e: OptException =>
        err println e.getMessage
        sys exit 1
      case e: Exception =>
        err println s"internal error: ${e.getMessage}"
        err println ">> stack trace"
        e printStackTrace err
        sys exit 1
    }
  }

  private val Usage = """usage: life -x SIZE -y SIZE OPTIONS
       life [-? | --help]

  Runs a simulation of Conway's Game of Life on the console.

  The -x and -y options should ideally be set to $COLUMNS and $LINES,
  respectively.

  If --fancy is specified, cells for each generation are displayed as follows:
    `+` if born
    `#` if alive, but born in prior generation
    `-` if died

required:
  -x SIZE                    : width of universe
  -y SIZE                    : height of universe

optional:
  --start COUNT              : number of living cells at start; 0 means derive
                               from universe size (default=0)
  --gen GENS                 : number of generations to simulate; 0 is forever
                               (default=0)
  --delay MILLIS             : delay in milliseconds before next generation
                               (default=500)
  --fancy                    : use fancy display (default is basic)
"""

  private val options =
    ("help", '?') ~> enable ~~ false ++
    'x' ~> as { (arg, _) =>
      val x = arg.toInt
      if (x <= 0) yell("must be > 0") else Some(x)
    } ~~ None ++
    'y' ~> as { (arg, _) =>
      val y = arg.toInt
      if (y <= 0) yell("must be > 0") else Some(y)
    } ~~ None ++
    "start" ~> as { (arg, _) =>
      val start = arg.toInt
      if (start < 0) yell("must be >= 0") else start
    } ~~ 0 ++
    "gen" ~> as { (arg, _) =>
      val gens = arg.toInt
      if (gens < 0) yell("must be >= 0") else gens
    } ~~ 0 ++
    "delay" ~> as { (arg, _) =>
      val delay = arg.toInt
      if (delay < 0) yell("must be >= 0") else delay
    } ~~ 500 ++
    "fancy" ~> enable ~~ false

  def run(args: Array[String]): Unit = {
    implicit val opts = options parse args
    if (opts[Boolean]("help"))
      println(Usage)
    else {
      val x = required[Int]("x")
      val y = required[Int]("y")
      val start = opts[Int]("start") match {
        case 0 => x * y / 10
        case n => n
      }
      val gens = opts[Int]("gen")
      val delay = opts[Int]("delay")
      val display = if (opts[Boolean]("fancy")) FancyDisplay(x, y) else BasicDisplay(x, y)

      @tailrec def simulate(universe: Universe): Unit = {
        display render universe
        Thread sleep delay
        if (gens == 0 || universe.generation < gens) simulate(universe.tick)
      }
      simulate(FiniteUniverse(x, y, start))
    }
  }

  private def required[A](name: String)(implicit opts: OptResult): A =
    opts[Option[A]](name) getOrElse yell(s"$name: required")
}
