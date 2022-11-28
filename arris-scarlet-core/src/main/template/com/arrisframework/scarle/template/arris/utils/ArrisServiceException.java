/*
 * 文  件  名：ScarleServiceException.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-2-23
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.arris.utils;

import com.arrisframework.common.exception.ArrisException;

/**
 * site Service Exception
 *
 * @author arris
 * @version C01 2015-2-23
 * @since
 */
public class ArrisServiceException extends ArrisException {
    /**
     * serial Version UID
     */
    private static final long serialVersionUID = 427388744384930525L;

    /**
     * 带异常码的构造函数
     *
     * @param code 异常码
     */
    public ArrisServiceException(String code) {
        super(code);
    }

    /**
     * 带异常码、异常描述的构造函数
     *
     * @param resultCode 返回码枚举
     */
    public ArrisServiceException(ResultCode resultCode) {
        super(resultCode.getCode(), resultCode.getDesc());
    }

    /**
     * 带异常码、异常描述和异常堆栈的构造函数
     *
     * @param resultCode 返回码枚举
     * @param cause 异常
     */
    public ArrisServiceException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getCode(), resultCode.getDesc(), cause);
    }

    /**
     * 带异常码、异常描述的构造函数
     *
     * @param code 异常码
     * @param message 异常描述
     */
    public ArrisServiceException(String code, String message) {
        super(code, message);
    }

    /**
     * 带异常码、异常描述和异常堆栈的构造函数
     *
     * @param exceptionCode 异常码
     * @param message 异常消息
     * @param cause 异常
     */
    public ArrisServiceException(String exceptionCode, String message, Throwable cause) {
        super(exceptionCode, message, cause);
    }

    /**
     * 带异常码和异常参数信息的构造函数
     *
     * @param code 异常码
     * @param args 异常参数，不提供国际化
     */
    public ArrisServiceException(String code, Object args) {
        super(code, args);
    }

    /**
     * 带异常码和一组异常参数的构造函数
     *
     * @param code 异常码
     * @param args 异常参数，不提供国际化
     */
    public ArrisServiceException(String code, Object[] args) {
        super(code, args);
    }

    /**
     * 带异常码和一组异常参数的构造函数
     *
     * @param code 异常码
     * @param args 异常参数，不提供国际化
     * @param cause 引发异常的原因
     */
    public ArrisServiceException(String code, Object[] args, Throwable cause) {
        super(code, args, cause);
    }

}
