package model.utils

import scala.util.Random

object PageNameGenerator {
  private val rng: Random = new Random()

  // TODO: Ensure no duplicates
  def newPageName: String = {
    val newPage: String = Integer.toString(rng.nextInt(Math.pow(2, 32).toInt), 16)

    newPage
  }
}
