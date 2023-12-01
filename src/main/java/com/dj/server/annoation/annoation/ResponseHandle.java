package com.dj.server.annoation.annoation;

import com.dj.server.annoation.inter.ResponseHandleInterceptor;

import java.lang.annotation.*;

/**
 * @author 戴军
 * 脱敏类型
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ResponseHandle {
    Class<? extends ResponseHandleInterceptor> value();
}
