/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.tuple

/**
 * Java API Represents a pair (tuple) of two elements.
 *
 * Additional tuple types for 3 to 22 values are defined in the `akka.japi.tuple` package, e.g.
 * [[scala.interopt.java.tuple.Tuple3]].
 */
@SerialVersionUID(1L)
case class Pair[A, B](first: A, second: B) {
  def toScala: (A, B) = (first, second)
}
object Pair {
  def create[A, B](first: A, second: B): Pair[A, B] = new Pair(first, second)
}
