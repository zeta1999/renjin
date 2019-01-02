/*
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-2019 BeDataDriven Groep B.V. and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, a copy is available at
 * https://www.gnu.org/licenses/gpl-2.0.txt
 */
package org.renjin.gcc.codegen.vptr;


import org.renjin.gcc.codegen.MethodGenerator;
import org.renjin.gcc.codegen.expr.Expressions;
import org.renjin.gcc.codegen.expr.GExpr;
import org.renjin.gcc.codegen.expr.JExpr;
import org.renjin.gcc.codegen.type.ReturnStrategy;
import org.renjin.gcc.codegen.type.TypeStrategy;
import org.renjin.gcc.runtime.Ptr;
import org.renjin.repackaged.asm.Type;

public class VPtrReturnStrategy implements ReturnStrategy {
  @Override
  public Type getType() {
    return Type.getType(Ptr.class);
  }

  @Override
  public JExpr marshall(GExpr expr) {
    return expr.toVPtrExpr().getRef();
  }

  @Override
  public GExpr unmarshall(MethodGenerator mv, JExpr callExpr, TypeStrategy lhsTypeStrategy) {
    return new VPtrExpr(callExpr);
  }

  @Override
  public JExpr getDefaultReturnValue() {
    return Expressions.nullRef(Type.getType(Ptr.class));
  }
}
