package com.loopfor.life

class FancyDisplay(xscale: Int, yscale: Int) extends Display {
  private val BlackOn = "\033[40m"
  private val RedOn = "\033[41m"
  private val GreenOn = "\033[42m"
  private val BlueOn = "\033[44m"

  def render(u: Universe): Unit = {
    val alive = u.alive
    val died = u.died
    val born = u.born
    print("\033[2J\033[0;0H" +
      (for (y <- 0 until yscale) yield {
        (for (x <- 0 until xscale) yield {
          val color = if (born(x, y)) BlueOn
            else if (alive(x, y)) GreenOn
            else if (died(x, y)) RedOn
            else BlackOn
          color + " "
        }).mkString + "\n"
      }).mkString + BlackOn)
  }
}

object FancyDisplay {
  def apply(xscale: Int, yscale: Int): FancyDisplay = new FancyDisplay(xscale, yscale)
}
