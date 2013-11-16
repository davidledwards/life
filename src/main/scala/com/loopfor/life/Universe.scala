package com.loopfor.life

trait Universe {
  def alive: Set[Point]
  def died: Set[Point]
  def born: Set[Point]
  def tick: Universe
}
