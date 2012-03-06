package r.compiler;

import java.io.PrintWriter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import r.compiler.cfg.BasicBlock;
import r.compiler.cfg.ControlFlowGraph;
import r.compiler.ir.tac.IRBody;
import r.compiler.ir.tac.expressions.IRThunk;
import r.compiler.ir.tac.statements.Statement;
import r.lang.Closure;

/**
 * Compiles a Thunk to a subclass of a Promise sexp 
 */
public class ThunkCompiler implements Opcodes {

  private IRThunk thunk;
  private ClassWriter cw;
  private ClassVisitor cv;
  private GenerationContext generationContext;

  public static Class<Closure> compileAndLoad(Closure closure) {
    return new ClosureCompiler("Closure" + System.identityHashCode(closure))
      .doCompileAndLoad(closure);
  }
  
  public static byte[] compile(String className, ThunkMap thunkMap, IRThunk thunk) {
    return new ThunkCompiler(thunkMap, className)
    .doCompile(thunk);    
  }
  
  public ThunkCompiler(ThunkMap thunkMap, String className) {
    super();
    
    this.generationContext = new GenerationContext(className,
        thunkMap);
  }

  public byte[] doCompile(IRThunk thunk) {
    this.thunk = thunk;
    startClass();
    writeStaticDoEval();
    writeConstructor();
    writeSexp();
    generationContext.getSexpPool().writeFields(cv);
    writeClassEnd();
    return cw.toByteArray();
  }

  private void startClass() {
    cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    //cw = new ClassWriter(0);
    cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
  //  cv = new CheckClassAdapter(cv);
    cv.visit(V1_6, ACC_PUBLIC + ACC_SUPER, generationContext.getClassName(), null, "r/lang/Promise", null);

  }

  private void writeConstructor() {
    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "(Lr/lang/Context;Lr/lang/Environment;)V", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitVarInsn(ALOAD, 2);
    mv.visitMethodInsn(INVOKESTATIC, "r/compiler/runtime/CompiledThunkExample", "createSexp", "()Lr/lang/SEXP;");
    mv.visitMethodInsn(INVOKESPECIAL, "r/lang/Promise", "<init>", "(Lr/lang/Context;Lr/lang/Environment;Lr/lang/SEXP;)V");
      
    // initialize sexp pool
  
    ConstantGeneratingVisitor cgv = new ConstantGeneratingVisitor(mv);
    for(SexpPool.Entry entry : generationContext.getSexpPool().entries()) {
      mv.visitInsn(DUP); // keep "this" on the stack
      entry.getSexp().accept(cgv);
      mv.visitFieldInsn(PUTFIELD, 
          generationContext.getClassName(), entry.getFieldName(), entry.getType());
    }
    
    mv.visitInsn(RETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
  }

  private void writeStaticDoEval() {  
    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "doEval", "(Lr/lang/Context;Lr/lang/Environment;)Lr/lang/SEXP;", null, null);
    mv.visitCode();
    writeDoEvalBody(mv);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
  }
  
  private void writeSexpPool() {
    for(SexpPool.Entry entry : generationContext.getSexpPool().entries()) {
      cv.visitField(ACC_PRIVATE, entry.getFieldName(), 
          entry.getType(), null, null);
    }
  }
  
  private void writeSexp() {
    MethodVisitor mv = cv.visitMethod(ACC_PRIVATE + ACC_STATIC, 
        "createBody", "()Lr/lang/SEXP;", null, null);
    mv.visitCode();
    ConstantGeneratingVisitor cgv = new ConstantGeneratingVisitor(mv);
    thunk.getSExpression().accept(cgv);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
 }
 
  private void writeDoEvalBody(MethodVisitor mv) {
    IRBody body = thunk.getBody();
    
    ByteCodeVisitor visitor = new ByteCodeVisitor(generationContext, mv);
    
    
    ControlFlowGraph cfg = new ControlFlowGraph(body);
    for(BasicBlock bb : cfg.getBasicBlocks()) {
      
      System.out.println(bb.statementsToString());
      
      visitor.startBasicBlock(bb);
      
    //  List<Statement> statements = TreeBuilder.build(bb);
      for(Statement stmt : bb.getStatements()) {
        stmt.accept(visitor);
      }
    }
    
  }

  private void writeClassEnd() {
    cv.visitEnd();
  }
}
