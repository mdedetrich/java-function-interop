/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

/**
 * Java API: Defines a criteria and determines whether the parameter meets this criteria. `Serializable` is needed to
 * be able to grab line number for Java 8 lambdas. Supports throwing `Exception` in the apply, which the
 * `java.util.function.Predicate` counterpart does not.
 */
@SerialVersionUID(1L)
@FunctionalInterface
trait Predicate[-T] extends java.io.Serializable {
  def test(param: T): Boolean
}
