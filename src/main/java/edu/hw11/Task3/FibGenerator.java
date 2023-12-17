package edu.hw11.Task3;

import java.io.FileOutputStream;
import java.io.IOException;
import net.bytebuddy.ByteBuddy;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

@SuppressWarnings({"MultipleStringLiterals", "UncommentedMain"})
public class FibGenerator {
    private FibGenerator() {

    }

    public static void main(String[] args) throws IOException {
        //generateAndSaveClass("FibonacciClass");
    }

    /*public static void generateAndSaveClass(String className) throws IOException {
        new ByteBuddy()
            .subclass(Object.class)
            .name(className)
            .method(FibonacciMethod())
            .make()
            .saveIn(FibGenerator.class.getProtectionDomain().getCodeSource().getLocation());

        byte[] byteCode = generateByteCode(className);
        try (FileOutputStream fos = new FileOutputStream(className + ".class")) {
            fos.write(byteCode);
        }
    }*/

    /*private static ByteCodeAppender FibonacciMethod() {

    }*/

    private static byte[] generateByteCode(String className) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        MethodVisitor mv;

        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);

        //Конструктор
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        //Fibonacci метод
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "fib", "(I)J", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitJumpInsn(Opcodes.IFLE, new Label());
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.I2L);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciCalculator", "fib", "(J)J", false);
        mv.visitInsn(Opcodes.LRETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    public static class FibonacciCalculator {
        public static long fib(long n) {
            if (n <= 1) {
                return n;
            }
            return fib(n - 1) + fib(n - 2);
        }
    }
}
