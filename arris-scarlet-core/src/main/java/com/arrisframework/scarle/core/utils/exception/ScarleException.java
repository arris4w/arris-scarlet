/*
 * 文  件  名：ScarleException.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-2-23
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.utils.exception;

/**
 * Scarle Exception
 *
 * @author arris
 * @version C01 2015-2-23
 * @since
 */
public class ScarleException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4121332335306452593L;

    /**
     * 默认异常
     */
    public ScarleException() {
        super();
    }

    /**
     * 构造一个带有描述消息的异常
     *
     * @param message 异常描述消息
     */
    public ScarleException(String message) {
        super(message);
    }

    /**
     * 构造一个异常，封装其它异常
     *
     * @param cause 其他异常
     */
    public ScarleException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造一个带有描述消息的异常，封装其它异常
     *
     * @param message 异常描述消息
     * @param cause 其他异常
     */
    public ScarleException(String message, Throwable cause) {
        super(message, cause);
    }
}
