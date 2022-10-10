/*
 * Copyright (C) 2009-2022 Lightbend Inc. <https://www.lightbend.com>
 */

package scala.interopt.java.partialfunction

import FI.{ Apply, Predicate, UnitApply }

private[partialfunction] object CaseStatement {
  def empty[F, T](): PartialFunction[F, T] = PartialFunction.empty
}

private[partialfunction] class CaseStatement[-F, +P, T](predicate: Predicate, apply: Apply[P, T]) extends PartialFunction[F, T] {

  override def isDefinedAt(o: F): Boolean = predicate.defined(o)

  override def apply(o: F): T = apply.apply(o.asInstanceOf[P])
}

private[partialfunction] class UnitCaseStatement[F, P](predicate: Predicate, apply: UnitApply[P]) extends PartialFunction[F, Unit] {

  override def isDefinedAt(o: F): Boolean = predicate.defined(o)

  override def apply(o: F): Unit = apply.apply(o.asInstanceOf[P])
}
