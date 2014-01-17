package com.loopfor.life

class FancyDisplay(xscale: Int, yscale: Int) extends Display {
  def render(u: Universe): Unit = {
    val alive = u.alive
    val died = u.died
    val born = u.born

    def glyph(x: Int, y: Int): Char =
      if (born(x, y)) '+' else if (alive(x, y)) '#' else if (died(x, y)) '-' else ' '

    print("\033[2J\033[0;0H" +
      (for (y <- 0 until yscale) yield {
        (for (x <- 0 until xscale) yield glyph(x, y)).mkString + "\n"
      }).mkString)
  }
}

object FancyDisplay {
  def apply(xscale: Int, yscale: Int): FancyDisplay = new FancyDisplay(xscale, yscale)
}
