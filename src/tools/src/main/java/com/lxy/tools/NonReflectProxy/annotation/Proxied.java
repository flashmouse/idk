package com.lxy.tools.NonReflectProxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.lxy.tools.NonReflectProxy.newCode.ICode;
import com.lxy.tools.NonReflectProxy.newCode.NullCode;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD,ElementType.TYPE })
@Inherited
public @interface Proxied {
	@SuppressWarnings("rawtypes")
	Class<? extends ICode> BeforeProxy() default NullCode.class;
	@SuppressWarnings("rawtypes")
	Class<? extends ICode> AfterProxy() default NullCode.class;
}
