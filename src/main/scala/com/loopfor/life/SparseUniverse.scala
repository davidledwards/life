package com.loopfor.life

class SparseUniverse(
  val generation: Int,
  prior: Set[Point],
  val died: Set[Point],
  val born: Set[Point])(implicit boundary: Point => Boolean) extends Universe {

  def this(prior: Set[Point])(implicit boundary: Point => Boolean) {
    this(0, prior, Set(), Set())
  }

  val alive: Set[Point] = prior -- died ++ born

  def tick: Universe = {
    new SparseUniverse(generation + 1, alive,
      alive filter { p => val l = p.liveNeighbors; l.size < 2 || l.size > 3 },
      alive flatMap { _.deadNeighbors } filter { _.liveNeighbors.size == 3 })
  }

  private implicit class Cell(point: Point) {
    private val (x, y) = point

    private val neighbors: Set[Point] = Set(
      (x + 1, y),
      (x - 1, y),
      (x, y + 1),
      (x, y - 1),
      (x + 1, y + 1),
      (x - 1, y + 1),
      (x + 1, y - 1),
      (x - 1, y - 1)) filter { boundary(_) }

    lazy val liveNeighbors: Set[Point] = neighbors filter { alive(_) }

    lazy val deadNeighbors: Set[Point] = neighbors filter { !alive(_) }
  }
}
