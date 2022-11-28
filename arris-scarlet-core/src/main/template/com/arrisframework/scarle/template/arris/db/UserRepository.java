/*
 * 文  件  名：UserRepository.java
 * 版         权：Copyright 2015-2025 ARRIS Rights Reserved.
 * 描         述：
 * 修  改  人：arris
 * 修改时间：2015-3-30
 * 修改内容：新增
 */
package com.arrisframework.scarle.template.arris.db;

import java.util.List;

import com.cheetah.commons.util.page.Page;
import com.arrisframework.scarle.template.arris.domain.User;

/**
 * 会员数据库操作接口
 *
 * @author arris
 * @version C01 2015-3-30
 */
public interface UserRepository {

    /**
     * 添加会员
     *
     * @author arris
     * @param user 会员对象
     * @return 会员标识
     */
    int save(User user);

    /**
     * 删除会员
     *
     * @author arris
     * @param userId 会员id
     * @return 影响的行
     */
    int delete(String userId);

    /**
     * 删除会员
     *
     * @author arris
     * @param userIds 会员id数组
     * @return 影响的行
     */
    int delete(String[] userIds);

    /**
     * 根据ID获取会员
     *
     * @author arris
     * @param userId 会员标识
     * @return 会员标识
     */
    User getById(String userId);

    /**
     * 修改会员
     *
     * @author arris
     * @param user 会员对象
     * @return 影响的行
     */
    int update(User user);

    /**
     * 分页获取会员列表
     *
     * @author arris
     * @param page 分页对象
     * @return 会员对象列表
     */
    List<User> list(Page page);

    /**
     * 获取会员总数量
     *
     * @author arris
     * @param page 分页对象
     * @return 会员数量
     */
    int listCount(Page page);

}
