package org.dandelion.service.impl;

import org.dandelion.model.dao.UserDAO;
import org.dandelion.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created on 2016-01-18.
 *
 * @author dolphineor
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;
}
