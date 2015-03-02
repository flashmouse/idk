package com.lxy.tools.proxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lxy.tools.proxy.newCode.ICode;
import com.lxy.tools.proxy.newCode.NullCode;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD,ElementType.TYPE })
@Inherited
public @interface Proxied {
	Class<? extends ICode> BeforeProxy() default NullCode.class;
	Class<? extends ICode> AfterProxy() default NullCode.class;
}
