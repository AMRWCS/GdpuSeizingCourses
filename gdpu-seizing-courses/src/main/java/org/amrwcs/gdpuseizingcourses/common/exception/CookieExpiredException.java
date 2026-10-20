package org.amrwcs.gdpuseizingcourses.common.exception;

/**
 * Cookie失效异常
 */
public class CookieExpiredException extends RuntimeException {
    public CookieExpiredException(String message) {
        super(message);
    }
}

