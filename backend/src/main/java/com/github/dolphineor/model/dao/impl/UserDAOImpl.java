package com.github.dolphineor.model.dao.impl;

import com.github.dolphineor.model.dao.UserDAO;
import com.github.dolphineor.model.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Resource
    private SqlSession sqlSession;

    @Override
    public void addUser(UserEntity user) {
        sqlSession.insert("createUser", user);
    }
}
