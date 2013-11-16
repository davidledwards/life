package com.loopfor.life

import scala.util.Random

object FiniteUniverse {
  def apply(xsize: Int, ysize: Int, alive: Set[Point]): Universe = {
    new SparseUniverse(alive)({ case (x, y) => x >= 0 && x < xsize && y >= 0 && y < ysize })
  }

  def apply(xsize: Int, ysize: Int, count: Int): Universe = {
    val alive = (for (_ <- 0 until count) yield (Random nextInt xsize, Random nextInt ysize)).toSet
    FiniteUniverse(xsize, ysize, alive)
  }
}