package org.lavenderx.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.lavenderx.model.dao.UserDAO;
import org.lavenderx.model.entity.UserEntity;
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
