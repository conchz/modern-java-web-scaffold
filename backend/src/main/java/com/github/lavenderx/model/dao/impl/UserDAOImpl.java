package com.github.lavenderx.model.dao.impl;

import com.github.lavenderx.model.dao.UserDAO;
import com.github.lavenderx.model.entity.UserEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void addUser(UserEntity user) {
        sqlSession.insert("createUser", user);
    }
}
