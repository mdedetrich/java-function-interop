/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

/**
 * An executable piece of code that takes no parameters and doesn't return any value. `Serializable` is needed to be
 * able to grab line number for Java 8 lambdas. Supports throwing `Exception` in the apply, which the
 * `java.util.function.Effect` counterpart does not.
 */
@SerialVersionUID(1L)
@FunctionalInterface
trait Effect extends java.io.Serializable {

  @throws(classOf[Exception])
  def apply(): Unit
}
