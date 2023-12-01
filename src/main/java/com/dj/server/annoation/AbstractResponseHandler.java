package com.dj.server.annoation;

import com.dj.server.annoation.annoation.ResponseHandle;
import com.dj.server.annoation.annoation.NeedResponseHandle;
import com.dj.server.annoation.handler.ResponseHandler;
import com.dj.server.annoation.inter.ResponseHandleInterceptor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author 戴军
 * 全局脱敏处理器
 */
public abstract class AbstractResponseHandler<T> implements ResponseBodyAdvice<T> {

    protected abstract Object getObject(T t);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Annotation[] methodAnnotations = methodParameter.getMethodAnnotations();
        for (Annotation annotation : methodAnnotations) {
            if(annotation instanceof NeedResponseHandle) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T beforeBodyWrite(T t, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        Object object = getObject(t);
        if(object == null) return t;
        Class<?> objectClass = object.getClass();

        while (objectClass != null) {
            Field[] fields = objectClass.getDeclaredFields();
            for (Field field : fields) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    if(annotation instanceof ResponseHandle) {
                        field.setAccessible(true);
                        Class<? extends ResponseHandleInterceptor> type = ((ResponseHandle) annotation).value();
                        ResponseHandler.hanlde(type, field, object);
                    }
                }
            }

            objectClass = objectClass.getSuperclass();
        }
        return t;
    }
}
