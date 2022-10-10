/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

/**
 * A Function interface. Used to create first-class-functions is Java. `Serializable` is needed to be able to grab line
 * number for Java 8 lambdas. Supports throwing `Exception` in the apply, which the `java.util.function.Function`
 * counterpart does not.
 */
@SerialVersionUID(1L)
@FunctionalInterface
trait Function[-T, +R] extends java.io.Serializable {
  @throws(classOf[Exception])
  def apply(param: T): R
}
