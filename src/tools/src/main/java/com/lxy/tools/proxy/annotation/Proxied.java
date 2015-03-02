package com.lxy.tools.proxy.annotation;

import com.lxy.tools.proxy.newCode.ICode;
import com.lxy.tools.proxy.newCode.NullCode;

import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface Proxied {
    Class<? extends ICode> BeforeProxy() default NullCode.class;

    Class<? extends ICode> AfterProxy() default NullCode.class;
}
