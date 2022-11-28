/*
 * 文  件  名：IUserService.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-3-31
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.cheeteh.service;

import com.cheetah.commons.util.page.Page;
import com.arrisframework.scarle.template.cheeteh.domain.User;
import com.arrisframework.scarle.template.cheeteh.utils.ScarleServiceException;

/**
 * 会员用户服务接口
 *
 * @author arris
 * @version C01 2015-3-31
 */
public interface IUserService
{

    /**
     * 会员注册
     *
     * @author arris
     * @param user
     *            会员对象
     * @throws ScarleServiceException
     *             ArrisServiceException
     */
    void add(User user) throws ScarleServiceException;

    /**
     * 删除会员信息
     *
     * @author arris
     * @param ids
     *            会员标识数据
     * @throws ScarleServiceException
     *             ArrisServiceException
     */
    void delete(String[] ids) throws ScarleServiceException;

    /**
     * 获取会员详情
     *
     * @author arris
     * @param userId
     *            会员标识
     * @return 对应会员对象
     * @throws ScarleServiceException
     *             ArrisServiceException
     */
    User get(String userId) throws ScarleServiceException;

    /**
     * 修改会员
     *
     * @author arris
     * @param user
     *            会员对象
     * @return 是否修改成功
     * @throws ScarleServiceException
     *             ArrisServiceException
     */
    boolean modify(User user) throws ScarleServiceException;

    /**
     * 分页获取会员列表数据
     *
     * @author arris
     * @param page
     *            分页对象
     * @throws ScarleServiceException
     *             ArrisServiceException
     */
    void list(Page page) throws ScarleServiceException;
}
