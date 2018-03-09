package com.lipenglong.ldubbo.rpc.proxy;

import com.lipenglong.ldubbo.rpc.LdubboInvoker;
import javassist.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成代理类的工厂类
 * </p>
 * Created by lipenglong on 2017/9/1.
 */
public class ProxyFactory {
    private static AtomicInteger num = new AtomicInteger(0);
    private static AtomicInteger serialNo = new AtomicInteger(1);

    /**
     * 生成代理类方法
     *
     * @param interfaceClass 要生成代理的接口类
     * @param invoker        netty client端通信invoker
     * @return 代理类
     */
    public static <T> T createProxy(Class<?> interfaceClass, LdubboInvoker invoker) {
        try {
            String interfaceName = interfaceClass.getName();
            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("com.lipenglong.ldubbo.rpc");
            pool.importPackage("com.lipenglong.ldubbo.common");
            //创建类，添加接口继承
            CtClass proxyClass = pool.makeClass("com.lipenglong.ldubbo.bytecode.Proxy" + num.getAndIncrement());
            proxyClass.addInterface(pool.get(interfaceName));
            //添加invoker成员变量和set方法
            CtField ctField = CtField.make("private LdubboInvoker invoker;", proxyClass);
            proxyClass.addField(ctField);
            CtMethod setInvoker = CtMethod.make("public void setInvoker(LdubboInvoker invoker) {\n" +
                    "this.invoker = invoker;}", proxyClass);
            proxyClass.addMethod(setInvoker);
            //添加接口方法实现
            for (Method method : interfaceClass.getMethods()) {
                StringBuffer src = new StringBuffer("");
                src.append(Modifier.toString(method.getModifiers()).replace("abstract", ""));
                src.append(method.getReturnType().getName() + " ");
                src.append(method.getName() + "(");
                src.append(getParameters(method.getParameterTypes()) + ") {\n");
                src.append("Invocation invocation = new Invocation(\"" + method.getName() +
                        "\", " + getParamTypesArray(method.getParameterTypes()) + ", " +
                        getParamsArray(method.getParameterTypes()) + ");\n");
                src.append("invocation.setServiceKey(\"" + interfaceName + "\");\n");
                src.append("invocation.setSerialNo(\"ser" + serialNo.getAndIncrement() + "\");\n");
                src.append("invoker.addInvocation(invocation);\n");
                src.append("boolean hasReturn = false;\n");
                src.append("while(!hasReturn) {\n" +
                        "       Result result = invoker.takeResult(invocation.getSerialNo());\n" +
                        "       if(result == null) {\n" +
                        "           new LdubboSleep().sleep(100L);\n" +
                        "       } else {\n" +
                        "           return (" + method.getReturnType().getName() + ") result.getValue();\n" +
                        "       }\n" +
                        "   }\n");
                src.append("return null;}");

                CtMethod ctMethod = CtNewMethod.make(src.toString(), proxyClass);
                proxyClass.addMethod(ctMethod);
            }
            proxyClass.toClass();
            Object proxy = Class.forName(proxyClass.getName()).newInstance();
            proxy.getClass().getMethod("setInvoker", LdubboInvoker.class).invoke(proxy, invoker);
            return (T) proxy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getParamsArray(Class<?>[] parameterTypes) {
        if (parameterTypes.length > 0) {
            StringBuffer buffer = new StringBuffer("new Object[]{");
            for (int i = 0; i < parameterTypes.length; i++) {
                buffer.append("_p" + i + ",");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append("}");
            return buffer.toString();
        }
        return null;
    }

    private static String getParameters(Class<?>[] parameterTypes) {
        StringBuffer buffer = new StringBuffer();
        Object o = new Class[]{java.lang.Long.class};
        if (parameterTypes.length > 0) {
            for (int i = 0; i < parameterTypes.length; i++) {
                Class paramType = parameterTypes[i];
                buffer.append(paramType.getName() + " _p" + i + ",");
            }
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }

    private static String getParamTypesArray(Class<?>[] parameterTypes) {
        if (parameterTypes.length > 0) {
            StringBuffer buffer = new StringBuffer("new Class[]{");
            for (Class type : parameterTypes) {
                buffer.append(type.getName() + ".class,");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            buffer.append("}");
            return buffer.toString();
        }
        return null;
    }
}
