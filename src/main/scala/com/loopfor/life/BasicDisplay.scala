package com.loopfor.life

class BasicDisplay(xscale: Int, yscale: Int) extends Display {
  def render(u: Universe): Unit = {
    val alive = u.alive
    print("\033[2J\033[0;0H" +
      (for (y <- 0 until yscale) yield {
        (for (x <- 0 until xscale) yield if (alive(x, y)) '#' else ' ').mkString + "\n"
      }).mkString)
  }
}

object BasicDisplay {
  def apply(xscale: Int, yscale: Int): BasicDisplay = new BasicDisplay(xscale, yscale)
}
