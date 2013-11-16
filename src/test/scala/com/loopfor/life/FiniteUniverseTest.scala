package com.loopfor.life

object FiniteUniverseTest {
  def main(args: Array[String]): Unit = {
    val u = FiniteUniverse(100, 100, 500)
    (u /: (0 until 32)) { case (u, gen) =>
      println(s"gen $gen: alive: ${u.alive.size}, died: ${u.died.size}, born: ${u.born.size}")
      u.tick
    }
  }
}