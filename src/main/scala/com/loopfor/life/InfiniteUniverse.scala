package com.loopfor.life

import scala.util.Random

class InfiniteUniverse(prior: Set[Point], val died: Set[Point], val born: Set[Point]) extends Universe {
  val alive: Set[Point] = prior -- died ++ born

  def tick: Universe = {
    val d = alive filter { p =>
      val live = p.liveNeighbors
      live.size < 2 || live.size > 3
    }
    val b = alive flatMap { _.deadNeighbors } filter { _.liveNeighbors.size == 3 }
    new InfiniteUniverse(alive, d, b)
  }

  private implicit class Cell(point: Point) {
    private val (x, y) = point

    private val neighbors: Set[Point] = Set(
      (x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1),
      (x + 1, y + 1), (x - 1, y + 1), (x + 1, y - 1), (x - 1, y - 1))

    lazy val liveNeighbors: Set[Point] = neighbors filter { alive(_) }

    lazy val deadNeighbors: Set[Point] = neighbors filter { !alive(_) }
  }
}

object InfiniteUniverse {
  def apply(alive: Set[Point]): InfiniteUniverse = new InfiniteUniverse(Set(), Set(), alive)

  def apply(count: Int, xlimit: Int, ylimit: Int): InfiniteUniverse = {
    val xoffset = xlimit / 2
    val yoffset = ylimit / 2
    def randomPoint() = (Random.nextInt(xlimit) - xoffset, Random.nextInt(ylimit) - yoffset)
    val alive = (for (_ <- 0 until count) yield randomPoint()).toSet
    InfiniteUniverse(alive)
  }
}
