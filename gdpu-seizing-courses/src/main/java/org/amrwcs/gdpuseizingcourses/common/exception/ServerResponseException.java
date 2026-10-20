package org.amrwcs.gdpuseizingcourses.common.exception;

/**
 * 教务系统响应异常
 */
public class ServerResponseException extends RuntimeException{
    public ServerResponseException(String message) {
        super(message);
    }
}
