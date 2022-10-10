/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

/**
 * A Function interface. Used to create 2-arg first-class-functions is Java. `Serializable` is needed to be able to
 * grab line number for Java 8 lambdas. Supports throwing `Exception` in the apply, which the
 * `java.util.function.BiFunction` counterpart does not.
 */
@SerialVersionUID(1L)
@FunctionalInterface
trait Function2[-T1, -T2, +R] extends java.io.Serializable {
  @throws(classOf[Exception])
  def apply(arg1: T1, arg2: T2): R
}
