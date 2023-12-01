package com.dj.server.annoation.handler;

import com.dj.server.annoation.inter.ResponseHandleInterceptor;

import java.lang.reflect.Field;

/**
 * @author 戴军
 * 脱敏处理器
 */
public class ResponseHandler {
    public static void hanlde(Class<? extends ResponseHandleInterceptor> aClass, Field field, Object object) {
        try {
            ResponseHandleInterceptor inter = aClass.newInstance();
            field.set(object,inter.handle(field.get(object)));
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
