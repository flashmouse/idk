import java.io.IOException;
 4 
 5 import org.objectweb.asm.AnnotationVisitor;
 6 import org.objectweb.asm.Attribute;
 7 import org.objectweb.asm.ClassReader;
 8 import org.objectweb.asm.ClassVisitor;
 9 import org.objectweb.asm.FieldVisitor;
10 import org.objectweb.asm.MethodVisitor;
11 
12 public class ClassReaderExample {
13     private static class MyClassVisitor implements ClassVisitor {
14 
15         @Override
16         public void visit(int version, int access, String name,
17                 String signature, String superName, String[] interfaces) {
18             System.out.println("class name:" + name);
19             System.out.println("super class name:" + superName);
20             System.out.println("class version:" + version);
21             System.out.println("class access:" + access);
22             System.out.println("class signature:" + signature);
23             if(interfaces != null && interfaces.length > 0){
24                 for(String str : interfaces){
25                     System.out.println("implemented interface name:" + str);
26                 }
27             }
28             
29         }
30 
31         @Override
32         public void visitSource(String source, String debug) {
33         }
34 
35         @Override
36         public void visitOuterClass(String owner, String name, String desc) {
37         }
38 
39         @Override
40         public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
41             return null;
42         }
43 
44         @Override
45         public void visitAttribute(Attribute attr) {
46         
47         }
48 
49         @Override
50         public void visitInnerClass(String name, String outerName,
51                 String innerName, int access) {
52         }
53 
54         @Override
55         public FieldVisitor visitField(int access, String name, String desc,
56                 String signature, Object value) {
57             return null;
58         }
59 
60         @Override
61         public MethodVisitor visitMethod(int access, String name, String desc,
62                 String signature, String[] exceptions) {
63             return null;
64         }
65 
66         @Override
67         public void visitEnd() {
68             
69         }
70     }
71     
72     public static void main(String args[]) throws IOException{
73         ClassReader classReader = new ClassReader("java.lang.String");
74         classReader.accept(new MyClassVisitor(), 0);
75     }
76 }