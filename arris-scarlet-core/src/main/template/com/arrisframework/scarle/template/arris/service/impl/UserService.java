/*
 * 文  件  名：UserService.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-3-27
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.arris.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.arrisframework.common.StringUtils;
import com.cheetah.commons.util.page.Page;
import com.arrisframework.scarle.template.arris.db.UserRepository;
import com.arrisframework.scarle.template.arris.domain.User;
import com.arrisframework.scarle.template.arris.service.IUserService;
import com.arrisframework.scarle.template.arris.utils.ArrisServiceException;
import com.arrisframework.scarle.template.arris.utils.ResultCode;

/**
 * 会员用户服务层
 *
 * @author arris
 * @version C01 2015-3-27
 */
@Service
public class UserService implements IUserService {

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
     * @param user 会员对象
     * @throws ArrisServiceException ArrisServiceException
     */
    public void add(User user) throws ArrisServiceException {
        if (null == user) {
            log.error("Param invalid: the 'user' is null.");
            throw new ArrisServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        userRepository.save(user);
    }

    /**
     * 删除会员信息
     *
     * @author arris
     * @param userIds 会员标识数据
     * @throws ArrisServiceException ArrisServiceException
     */
    public void delete(String userId) throws ArrisServiceException {
        if (StringUtils.isEmpty(userId)) {
            log.error("Param invalid: the 'userId' is invalid.");
            throw new ArrisServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        userRepository.delete(userId);
    }

    /**
     * 删除会员信息
     *
     * @author arris
     * @param userIds 会员标识数据
     * @throws ArrisServiceException ArrisServiceException
     */
    public void delete(String[] userIds) throws ArrisServiceException {
        if (null == userIds || userIds.length == 0) {
            log.error("Param invalid: the 'user' or 'userId' is invalid.");
            throw new ArrisServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        userRepository.delete(userIds);
    }

    /**
     * 获取会员详情
     *
     * @author arris
     * @param userId 会员标识
     * @return 对应会员对象
     * @throws ArrisServiceException ArrisServiceException
     */
    public User get(String userId) throws ArrisServiceException {
        if (StringUtils.isEmpty(userId)) {
            log.error("Param invalid: the 'userId' is null.");
            throw new ArrisServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        return userRepository.getById(userId);
    }

    /**
     * 修改会员
     *
     * @author arris
     * @param user 会员对象
     * @return 是否修改成功
     * @throws ArrisServiceException ArrisServiceException
     */
    public boolean modify(User user) throws ArrisServiceException {
        if (null == user || StringUtils.isEmpty(user.getUserId())) {
            log.error("Param invalid: the 'user' or 'userId' is invalid.");
            throw new ArrisServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        int result = userRepository.update(user);
        return result == 1 ? true : false;
    }

    /**
     * 分页获取会员列表数据
     *
     * @author arris
     * @param page 分页对象
     * @throws ArrisServiceException ArrisServiceException
     */
    public void list(Page page) throws ArrisServiceException {
        if (null == page) {
            log.error("Param invalid: the 'page' is null.");
            throw new ArrisServiceException(ResultCode.REQUEST_PARAM_ERROR);
        }

        List<User> users = userRepository.list(page);
        int totalRecords = userRepository.listCount(page);

        page.setTotalRecords(totalRecords);
        page.setCollection(users);
    }
}
