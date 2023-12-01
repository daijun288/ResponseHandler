package com.dj.server.annoation.annoation;

import java.lang.annotation.*;

/**
 * @author 戴军
 * 哪些方法需要脱敏
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NeedResponseHandle {
}
