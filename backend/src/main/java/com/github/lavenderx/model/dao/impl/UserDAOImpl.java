package com.github.lavenderx.model.dao.impl;

import com.github.lavenderx.model.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import com.github.lavenderx.model.dao.UserDAO;
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
