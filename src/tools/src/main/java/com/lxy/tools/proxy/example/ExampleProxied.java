package com.lxy.tools.proxy.example;

import com.lxy.tools.proxy.annotation.Proxied;
import com.lxy.tools.proxy.newCode.ExampleAfterCode;
import com.lxy.tools.proxy.newCode.ExampleCode;

public class ExampleProxied {
    //	@Proxied(BeforeProxy=ExampleCode.class,AfterProxy=ExampleAfterCode.class)
    @Proxied(BeforeProxy = ExampleCode.class)
    public void test() {
        System.out.println("原来的函数");
    }

    @Proxied(BeforeProxy = ExampleCode.class, AfterProxy = ExampleAfterCode.class)
    public void after() {
    }

    public ExampleProxied() {
        System.out.println("...");
    }
}
