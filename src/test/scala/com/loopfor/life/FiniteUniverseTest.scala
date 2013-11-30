package com.loopfor.life

import scala.annotation.tailrec

object FiniteUniverseTest {
  def main(args: Array[String]): Unit = {
    val xscale = args(0).toInt
    val yscale = args(1).toInt
    val initial = args(2).toInt
    val delay = args(3).toInt
    val cycles = args(4).toInt
    val display = FancyDisplay(xscale, yscale)
    @tailrec def run(u: Universe): Unit = {
      display render u
      Thread sleep delay
      if (u.generation < cycles) run(u.tick)
    }
    run(FiniteUniverse(xscale, yscale, initial))
  }
}
