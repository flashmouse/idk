package com.lxy.tools.proxy;

import com.lxy.tools.proxy.example.ExampleProxied;

public class Hello {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        StartWork sw = StartWork.getInstance();
        sw.execute();

        ExampleProxied ep = (ExampleProxied) NonReflectProxyFactory.getProxiedObject(ExampleProxied.class);
        ep.test();
    }

}
