package com.loopfor.life

import scala.annotation.tailrec

object Test {
  def main(args: Array[String]): Unit = {
    val u: Universe = InfiniteUniverse(500, 100, 100)
    (u /: (0 until 16)) { case (u, gen) =>
      println(s"gen $gen: alive: ${u.alive.size}, died: ${u.died.size}, born: ${u.born.size}")
      u.tick
    }
  }
}
