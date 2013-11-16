package com.loopfor.life

object InfiniteUniverserTest {
  def main(args: Array[String]): Unit = {
    val u = InfiniteUniverse(500, 100, 100)
    (u /: (0 until 32)) { case (u, gen) =>
      println(s"gen $gen: alive: ${u.alive.size}, died: ${u.died.size}, born: ${u.born.size}")
      u.tick
    }
  }
}
