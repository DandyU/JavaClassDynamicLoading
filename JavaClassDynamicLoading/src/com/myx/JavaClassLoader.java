package com.myx;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassLoader extends ClassLoader {

    public void invokeClassMethod(String className, String methodName){
        try {
            log("# Load internal class and call method");
            loadInternalClass(className, methodName);

            log("# Load external class and call method");
            loadExternalClass(className, methodName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loadInternalClass(String className, String methodName) throws ClassNotFoundException, Exception {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class loadedMyClass = classLoader.loadClass(className);
        log("Loaded class name: " + loadedMyClass.getName());

        Constructor constructor = loadedMyClass.getConstructor();
        Object myClassObject = constructor.newInstance();
        Method method = loadedMyClass.getMethod(methodName);
        log("Method name: " + method.getName());

        method.invoke(myClassObject);
    }

    private void loadExternalClass(String className, String methodName) throws ClassNotFoundException, Exception {
        File file = new File("D:/");
        URL url = file.toURI().toURL(); //convert the file to URL format
        URL[] urls = new URL[]{url};
        URLClassLoader urlClassLoader = new URLClassLoader(urls, null); // if parent parameter of URLClassLoader isn't null, classloader use the internal MyClass.

        Class loadedMyClass = urlClassLoader.loadClass(className); // if class name is "com.myx.MyClass", absolute path must be "D:/com/myx/MyClass.class".
        urlClassLoader.close();
        log("Loaded class name: " + loadedMyClass.getName());

        Constructor constructor = loadedMyClass.getConstructor();
        Object myClassObject = constructor.newInstance();
        Method method = loadedMyClass.getMethod(methodName);
        log("Method name: " + method.getName());

        method.invoke(myClassObject);
    }

    private void log(String log) {
        System.out.println(log);
    }

}
