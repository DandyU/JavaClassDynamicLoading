package com.myx;

public class Test {

    public static void main(String[] args) {
        JavaClassLoader javaClassLoader = new JavaClassLoader();
        javaClassLoader.invokeClassMethod("com.myx.MyClass", "sayHello");
    }

}
