/*
 * 文  件  名：Bean.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-5-19
 * 修改内容：新增
 */
package com.arrisframework.scarle.core.bean;

import java.util.List;

/**
 * Bean 对象
 * 
 * @author arris
 * @version C01 2015-5-19
 */
public class Bean {

    /**
     * Bean 名称
     */
    private Name name;

    /**
     * 注释
     */
    private String comment;

    /**
     * 主键
     */
    private List<Field> identifys;

    /**
     * 属性列表
     */
    private List<Field> fields;

    /**
     * 构造函数
     * 
     * @param name bean 名称
     * @param comment bean 注释
     * @param identifys 主键属性列表
     * @param fields 属性列表
     */
    public Bean(Name name, String comment, List<Field> identifys, List<Field> fields) {
        this.name = name;

        if (comment.endsWith("表")) {
            comment = comment.substring(0, comment.length() - 1);
        }

        this.comment = comment;
        this.identifys = identifys;
        this.fields = fields;
    }

    /**
     * 取得name
     * 
     * @return 返回name。
     */
    public Name getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name 要设置的name。
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * 取得comment
     * 
     * @return 返回comment。
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置comment
     * 
     * @param comment 要设置的comment。
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 取得identifys
     * 
     * @return 返回identifys。
     */
    public List<Field> getIdentifys() {
        return identifys;
    }

    /**
     * 设置identifys
     * 
     * @param identifys 要设置的identifys。
     */
    public void setIdentifys(List<Field> identifys) {
        this.identifys = identifys;
    }

    /**
     * 取得fields
     * 
     * @return 返回fields。
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * 设置fields
     * 
     * @param fields 要设置的fields。
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
