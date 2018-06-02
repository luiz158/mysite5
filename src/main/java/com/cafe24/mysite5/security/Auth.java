package com.cafe24.mysite5.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Auth {
	//enum 타입 정의
	public enum Role { ADMIN, USER }
	
	public Role role() default Role.USER;
	/*
	@Auth(value="user")라고 한 것과 같다
	public String value() default "user";
	public int test() default 1;*/


}
