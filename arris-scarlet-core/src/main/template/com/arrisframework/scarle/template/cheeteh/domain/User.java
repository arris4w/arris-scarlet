/*
 * 文  件  名：User.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-2-23
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.cheeteh.domain;

import java.util.Date;

/**
 * 用户对象
 *
 * @author arris
 * @version C01 2015-2-23
 * @since
 */
public class User
{
    /**
     * 用户标识
     */
    private String userId;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别(1:男；2:女；3:保密)
     */
    private int sex;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 注册来源
     */
    private String source;

    /**
     * 注册来源名称
     */
    private String sourceName;

    /**
     * 地区
     */
    private String areaId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 最后修改时间
     */
    private Date lastModifiedTime;

    /**
     * 用户状态 (1.正常|2.锁定)
     */
    private int userStatus;

    /**
     * 是否删除
     */
    private int isDelete;

    /**
     * 构造函数
     */
    public User()
    {

    }

    /**
     * 构造函数
     *
     * @param userId
     *            userId
     */
    public User(String userId)
    {
        this.userId = userId;
    }

    /**
     * 取得userId
     *
     * @return 返回userId。
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * 设置userId
     *
     * @param userId
     *            要设置的userId。
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
     * 取得mobile
     *
     * @return 返回mobile。
     */
    public String getMobile()
    {
        return mobile;
    }

    /**
     * 设置mobile
     *
     * @param mobile
     *            要设置的mobile。
     */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    /**
     * 取得password
     *
     * @return 返回password。
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * 设置password
     *
     * @param password
     *            要设置的password。
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * 取得salt
     *
     * @return 返回salt。
     */
    public String getSalt()
    {
        return salt;
    }

    /**
     * 设置salt
     *
     * @param salt
     *            要设置的salt。
     */
    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    /**
     * 取得nickName
     *
     * @return 返回nickName。
     */
    public String getNickName()
    {
        return nickName;
    }

    /**
     * 设置nickName
     *
     * @param nickName
     *            要设置的nickName。
     */
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    /**
     * 取得userName
     *
     * @return 返回userName。
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * 设置userName
     *
     * @param userName
     *            要设置的userName。
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * 取得sex
     *
     * @return 返回sex。
     */
    public int getSex()
    {
        return sex;
    }

    /**
     * 设置sex
     *
     * @param sex
     *            要设置的sex。
     */
    public void setSex(int sex)
    {
        this.sex = sex;
    }

    /**
     * 取得birthday
     *
     * @return 返回birthday。
     */
    public Date getBirthday()
    {
        return birthday;
    }

    /**
     * 设置birthday
     *
     * @param birthday
     *            要设置的birthday。
     */
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    /**
     * 取得identity
     *
     * @return 返回identity。
     */
    public String getIdentity()
    {
        return identity;
    }

    /**
     * 设置identity
     *
     * @param identity
     *            要设置的identity。
     */
    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    /**
     * 取得regTime
     *
     * @return 返回regTime。
     */
    public Date getRegTime()
    {
        return regTime;
    }

    /**
     * 设置regTime
     *
     * @param regTime
     *            要设置的regTime。
     */
    public void setRegTime(Date regTime)
    {
        this.regTime = regTime;
    }

    /**
     * 取得source
     *
     * @return 返回source。
     */
    public String getSource()
    {
        return source;
    }

    /**
     * 设置source
     *
     * @param source
     *            要设置的source。
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * 取得sourceName
     *
     * @return 返回sourceName。
     */
    public String getSourceName()
    {
        return sourceName;
    }

    /**
     * 设置sourceName
     *
     * @param sourceName
     *            要设置的sourceName。
     */
    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    /**
     * 取得areaId
     *
     * @return 返回areaId。
     */
    public String getAreaId()
    {
        return areaId;
    }

    /**
     * 设置areaId
     *
     * @param areaId
     *            要设置的areaId。
     */
    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    /**
     * 取得address
     *
     * @return 返回address。
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * 设置address
     *
     * @param address
     *            要设置的address。
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * 取得email
     *
     * @return 返回email。
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * 设置email
     *
     * @param email
     *            要设置的email。
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * 取得lastModifiedTime
     *
     * @return 返回lastModifiedTime。
     */
    public Date getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    /**
     * 设置lastModifiedTime
     *
     * @param lastModifiedTime
     *            要设置的lastModifiedTime。
     */
    public void setLastModifiedTime(Date lastModifiedTime)
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * 取得userStatus
     *
     * @return 返回userStatus。
     */
    public int getUserStatus()
    {
        return userStatus;
    }

    /**
     * 设置userStatus
     *
     * @param userStatus
     *            要设置的userStatus。
     */
    public void setUserStatus(int userStatus)
    {
        this.userStatus = userStatus;
    }

    /**
     * 取得isDelete
     *
     * @return 返回isDelete。
     */
    public int getIsDelete()
    {
        return isDelete;
    }

    /**
     * 设置isDelete
     *
     * @param isDelete
     *            要设置的isDelete。
     */
    public void setIsDelete(int isDelete)
    {
        this.isDelete = isDelete;
    }

}
