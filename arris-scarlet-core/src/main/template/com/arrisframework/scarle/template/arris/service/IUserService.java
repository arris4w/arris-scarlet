/*
 * 文  件  名：IUserService.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-3-31
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.arris.service;

import com.cheetah.commons.util.page.Page;
import com.arrisframework.scarle.template.arris.domain.User;
import com.arrisframework.scarle.template.arris.utils.ArrisServiceException;

/**
 * 会员用户服务接口
 *
 * @author arris
 * @version C01 2015-3-31
 */
public interface IUserService {

    /**
     * 会员注册
     *
     * @author arris
     * @param user 会员对象
     * @throws ArrisServiceException ArrisServiceException
     */
    void add(User user) throws ArrisServiceException;

    /**
     * 删除会员信息
     *
     * @author arris
     * @param ids 会员标识数据
     * @throws ArrisServiceException ArrisServiceException
     */
    void delete(String[] ids) throws ArrisServiceException;

    /**
     * 获取会员详情
     *
     * @author arris
     * @param userId 会员标识
     * @return 对应会员对象
     * @throws ArrisServiceException ArrisServiceException
     */
    User get(String userId) throws ArrisServiceException;

    /**
     * 修改会员
     *
     * @author arris
     * @param user 会员对象
     * @return 是否修改成功
     * @throws ArrisServiceException ArrisServiceException
     */
    boolean modify(User user) throws ArrisServiceException;

    /**
     * 分页获取会员列表数据
     *
     * @author arris
     * @param page 分页对象
     * @throws ArrisServiceException ArrisServiceException
     */
    void list(Page page) throws ArrisServiceException;
}
