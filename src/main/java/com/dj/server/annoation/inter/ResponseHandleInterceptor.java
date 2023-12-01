package com.dj.server.annoation.inter;

/**
 * @author 戴军
 * 脱敏接口
 */
public interface ResponseHandleInterceptor<T> {
    /**
     * 脱敏处理
     * @param value
     * @return
     */
    T handle(T value);

}
