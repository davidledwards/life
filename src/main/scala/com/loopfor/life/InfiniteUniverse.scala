package com.loopfor.life

import scala.util.Random

object InfiniteUniverse {
  def apply(alive: Set[Point]): Universe = {
    new SparseUniverse(alive)({ _ => true })
  }

  def apply(count: Int, xlimit: Int, ylimit: Int): Universe = {
    val alive = (for (_ <- 0 until count) yield (Random nextInt xlimit, Random nextInt ylimit)).toSet
    InfiniteUniverse(alive)
  }
}
