/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.function

import scala.runtime.AbstractPartialFunction
import scala.util.control.NoStackTrace

/**
 * Helper for implementing a *pure* partial function: it will possibly be
 * invoked multiple times for a single “application”, because its only abstract
 * method is used for both isDefinedAt() and apply(); the former is mapped to
 * `isCheck == true` and the latter to `isCheck == false` for those cases where
 * this is important to know.
 *
 * Failure to match is signaled by throwing `noMatch()`, i.e. not returning
 * normally (the exception used in this case is pre-allocated, hence not
 * <i>that</i> expensive).
 *
 * {{{
 * new JavaPartialFunction<Object, String>() {
 *   public String apply(Object in, boolean isCheck) {
 *     if (in instanceof TheThing) {
 *       if (isCheck) return null; // to spare the expensive or side-effecting code
 *       return doSomethingWithTheThing((TheThing) in);
 *     } else {
 *       throw noMatch();
 *     }
 *   }
 * }
 * }}}
 *
 * The typical use of partial functions from Akka looks like the following:
 *
 * {{{
 * if (pf.isDefinedAt(x)) {
 *   pf.apply(x);
 * }
 * }}}
 *
 * i.e. it will first call `JavaPartialFunction.apply(x, true)` and if that
 * does not throw `noMatch()` it will continue with calling
 * `JavaPartialFunction.apply(x, false)`.
 */
abstract class JavaPartialFunction[A, B] extends AbstractPartialFunction[A, B] {
  import JavaPartialFunction._

  @throws(classOf[Exception])
  def apply(x: A, isCheck: Boolean): B

  final def isDefinedAt(x: A): Boolean =
    try {
      apply(x, true); true
    } catch { case NoMatch => false }
  final override def apply(x: A): B =
    try apply(x, false)
    catch { case NoMatch => throw new MatchError(x) }
  final override def applyOrElse[A1 <: A, B1 >: B](x: A1, default: A1 => B1): B1 =
    try apply(x, false)
    catch { case NoMatch => default(x) }
}

object JavaPartialFunction {
  sealed abstract class NoMatchException extends RuntimeException with NoStackTrace

  case object NoMatch extends NoMatchException

  final def noMatch(): RuntimeException = NoMatch
}
