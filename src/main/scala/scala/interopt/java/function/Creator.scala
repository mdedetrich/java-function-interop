/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

import java.io.Serializable

/**
 * A constructor/factory, takes no parameters but creates a new value of type T every call. Supports throwing
 * `Exception` in the apply, which the `java.util.function.Creator` counterpart does not.
 */
@SerialVersionUID(1L)
@FunctionalInterface
trait Creator[+T] extends Serializable {

  /**
   * This method must return a different instance upon every call.
   */
  @throws(classOf[Exception])
  def create(): T
}
