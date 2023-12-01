package com.dj.sensitive.handler;


import com.dj.server.annoation.inter.ResponseHandleInterceptor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 戴军
 * 电话号码脱敏
 */
public class PhoneSensitiveHandler implements ResponseHandleInterceptor<String> {
    @Override
    public String handle(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
