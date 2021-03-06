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
package org.renjin.compiler.builtins;

import org.renjin.compiler.codegen.EmitContext;
import org.renjin.compiler.codegen.expr.CompiledSexp;
import org.renjin.compiler.codegen.expr.ScalarExpr;
import org.renjin.compiler.codegen.expr.VectorType;
import org.renjin.compiler.ir.TypeSet;
import org.renjin.compiler.ir.ValueBounds;
import org.renjin.compiler.ir.tac.IRArgument;
import org.renjin.repackaged.asm.commons.InstructionAdapter;
import org.renjin.sexp.FunctionCall;

import java.util.List;


public class LengthCall implements Specialization {

  private final ValueBounds bounds;

  public LengthCall(ValueBounds argumentBounds) {
    bounds = ValueBounds.builder()
        .setTypeSet(TypeSet.INT)
        .addFlags(ValueBounds.FLAG_NO_NA | ValueBounds.LENGTH_ONE)
        .addFlags(ValueBounds.FLAG_POSITIVE, argumentBounds.isFlagSet(ValueBounds.LENGTH_NON_ZERO))
        .build();
  }

  public ValueBounds getResultBounds() {
    return bounds;
  }

  @Override
  public boolean isPure() {
    return true;
  }

  @Override
  public CompiledSexp getCompiledExpr(EmitContext emitContext, FunctionCall call, List<IRArgument> arguments) {
    CompiledSexp vector = arguments.get(0).getExpression().getCompiledExpr(emitContext);
    return new ScalarExpr(VectorType.INT) {
      @Override
      public void loadScalar(EmitContext context, InstructionAdapter mv) {
        vector.loadLength(context, mv);
      }
    };
  }

}
