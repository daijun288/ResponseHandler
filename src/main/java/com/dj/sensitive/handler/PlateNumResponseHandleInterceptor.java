package com.dj.sensitive.handler;

import com.dj.server.annoation.inter.ResponseHandleInterceptor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 戴军
 * 车牌号脱敏
 */
public class PlateNumResponseHandleInterceptor implements ResponseHandleInterceptor<String> {
    @Override
    public String handle(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value.substring(0, 3) + "*" + value.substring(4);
    }
}
