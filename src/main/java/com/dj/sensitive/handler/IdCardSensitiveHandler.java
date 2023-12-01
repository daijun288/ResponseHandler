package com.dj.sensitive.handler;

import com.dj.server.annoation.inter.ResponseHandleInterceptor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 戴军
 * 身份证号码脱敏
 */
public class IdCardSensitiveHandler implements ResponseHandleInterceptor<String> {
    @Override
    public String handle(String value) {
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return StringUtils.left(value, 6)
                .concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(value, 4),
                        StringUtils.length(value), "*"), "***"));
    }
}
