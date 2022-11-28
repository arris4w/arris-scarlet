/*
 * 文  件  名：UserService.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-3-27
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.cheeteh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arrisframework.common.StringUtils;
import com.cheetah.commons.util.page.Page;
import com.arrisframework.scarle.template.cheeteh.domain.User;
import com.arrisframework.scarle.template.cheeteh.service.IUserService;
import com.arrisframework.scarle.template.cheeteh.system.db.UserRepository;
import com.arrisframework.scarle.template.cheeteh.utils.ResultCode;
import com.arrisframework.scarle.template.cheeteh.utils.ScarleServiceException;

/**
 * 会员用户服务层
 *
 * @author arris
 * @version C01 2015-3-27
 */
@Service
public class UserService implements IUserService
{

    /**
     * 日志实例
     */
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * 会员数据库操作
     */
    @Resource
    private UserRepository userRepository;

    /**
     * 添加会员
     *
     * @author arris
     * @param user
     *            会员对象
     * @throws ScarleServiceException
     *             ScarleServiceException
     */
    public void add(User user) throws ScarleServiceException
    {
        if (null == user)
        {
            log.error("Param invalid: the 'user' is null.");
            throw new ScarleServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        try
        {
            userRepository.save(user);
        }
        catch (Exception e)
        {
            log.error("Add user [" + user.getMobile() + "] has exception .", e);
            throw new ScarleServiceException(ResultCode.BUSINESS_PROCESS_FAILURE, e);
        }
    }

    /**
     * 删除会员信息
     *
     * @author arris
     * @param userIds
     *            会员标识数据
     * @throws ScarleServiceException
     *             ScarleServiceException
     */
    public void delete(String userId) throws ScarleServiceException
    {
        int a = 0;
        if (StringUtils.isEmpty(userId) || a > 0)
        {
            log.error("Param invalid: the 'userId' is invalid.");
            throw new ScarleServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        try
        {
            userRepository.delete(userId);
        }
        catch (Exception e)
        {
            log.error("Delete user [" + userId + "] has exception.", e);
            throw new ScarleServiceException(ResultCode.BUSINESS_PROCESS_FAILURE, e);
        }
    }

    /**
     * 删除会员信息
     *
     * @author arris
     * @param userIds
     *            会员标识数据
     * @throws ScarleServiceException
     *             ScarleServiceException
     */
    public void delete(String[] userIds) throws ScarleServiceException
    {
        if (null == userIds || userIds.length == 0)
        {
            log.error("Param invalid: the 'user' or 'userId' is invalid.");
            throw new ScarleServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        try
        {
            userRepository.delete(userIds);
        }
        catch (Exception e)
        {
            log.error("Delete user [" + userIds + "] has exception.", e);
            throw new ScarleServiceException(ResultCode.BUSINESS_PROCESS_FAILURE, e);
        }
    }

    /**
     * 获取会员详情
     *
     * @author arris
     * @param userId
     *            会员标识
     * @return 对应会员对象
     * @throws ScarleServiceException
     *             ScarleServiceException
     */
    public User get(String userId) throws ScarleServiceException
    {
        if (StringUtils.isEmpty(userId))
        {
            log.error("Param invalid: the 'userId' is null.");
            throw new ScarleServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        try
        {
            return userRepository.getById(userId);
        }
        catch (Exception e)
        {
            log.error("Get user [" + userId + "] detail has exception.", e);
            throw new ScarleServiceException(ResultCode.BUSINESS_PROCESS_FAILURE, e);
        }
    }

    /**
     * 修改会员
     *
     * @author arris
     * @param user
     *            会员对象
     * @return 是否修改成功
     * @throws ScarleServiceException
     *             ScarleServiceException
     */
    public boolean modify(User user) throws ScarleServiceException
    {
        if (null == user || StringUtils.isEmpty(user.getUserId()))
        {
            log.error("Param invalid: the 'user' or 'userId' is invalid.");
            throw new ScarleServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        try
        {
            int result = userRepository.update(user);
            return result == 1 ? true : false;
        }
        catch (Exception e)
        {
            log.error("Modify user [" + user.getUserId() + "] has exception .", e);
            throw new ScarleServiceException(ResultCode.BUSINESS_PROCESS_FAILURE, e);
        }
    }

    /**
     * 分页获取会员列表数据
     *
     * @author arris
     * @param page
     *            分页对象
     * @throws ScarleServiceException
     *             ScarleServiceException
     */
    public void list(Page page) throws ScarleServiceException
    {
        if (null == page)
        {
            log.error("Param invalid: the 'page' is null.");
            throw new ScarleServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        try
        {
            List<User> users = userRepository.list(page);
            int totalRecords = userRepository.listCount(page);

            page.setTotalRecords(totalRecords);
            page.setCollection(users);
        }
        catch (Exception e)
        {
            log.error("Get user page has exception .", e);
            throw new ScarleServiceException(ResultCode.BUSINESS_PROCESS_FAILURE, e);
        }
    }
}
