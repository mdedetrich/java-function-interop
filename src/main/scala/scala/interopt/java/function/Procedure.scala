/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

/**
 * A Procedure is like a Function, but it doesn't produce a return value. `Serializable` is needed to be able to grab
 * line number for Java 8 lambdas. Supports throwing `Exception` in the apply, which the `java.util.function.Consumer`
 * counterpart does not.
 */
@SerialVersionUID(1L)
@FunctionalInterface
trait Procedure[-T] extends java.io.Serializable {
  @throws(classOf[Exception])
  def apply(param: T): Unit
}
