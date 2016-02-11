package org.dandelion.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.dandelion.model.dao.UserDAO;
import org.dandelion.model.entity.UserEntity;
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
