/**
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-2016 BeDataDriven Groep B.V. and contributors
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
package org.renjin.gcc.peephole;

import org.renjin.repackaged.asm.Label;
import org.renjin.repackaged.asm.tree.AbstractInsnNode;
import org.renjin.repackaged.asm.tree.JumpInsnNode;
import org.renjin.repackaged.asm.tree.MethodNode;
import org.renjin.repackaged.asm.util.Textifier;
import org.renjin.repackaged.asm.util.TraceMethodVisitor;
import org.renjin.repackaged.guava.collect.Lists;
import org.renjin.repackaged.guava.collect.Sets;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Optimizes generated bytecode by replacing groups of instructions with more space efficient representations.
 *
 * <p>These sorts of optimizations don't appear to have much impact on the performance of compiled
 * methods, but it can drastically reduce the size of the generated bytecode.</p>
 */
public class PeepholeOptimizer {

  public static PeepholeOptimizer INSTANCE = new PeepholeOptimizer();

  private List<PeepholeOptimization> optimizations = Lists.newArrayList();

  public PeepholeOptimizer() {
    optimizations.add(new StoreLoad());
    optimizations.add(new LoadLoad());
    optimizations.add(new IntegerIncrement());
    optimizations.add(new ConstantMath());
  }

  public void optimize(MethodNode methodNode) {

    methodNode.accept(new TraceMethodVisitor(new Textifier()));

    NodeIt it = new NodeIt(methodNode.instructions, findJumpTargets(methodNode));
    do {
      boolean changing;
      do {
        changing = false;
        for (PeepholeOptimization optimization : optimizations) {
          if (optimization.apply(it)) {
            changing = true;
          }
        }
      } while (changing);
    } while (it.next());
  }

  private Set<Label> findJumpTargets(MethodNode methodNode) {
    Set<Label> targets = Sets.newHashSet();
    ListIterator<AbstractInsnNode> it = methodNode.instructions.iterator();
    while(it.hasNext()) {
      AbstractInsnNode node = it.next();
      if(node instanceof JumpInsnNode) {
        JumpInsnNode jumpNode = (JumpInsnNode) node;
        targets.add(jumpNode.label.getLabel());
      }
    }
    return targets;
  }
}