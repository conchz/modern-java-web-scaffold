package com.github.dolphineor.service.impl;

import com.github.dolphineor.model.dao.UserDAO;
import com.github.dolphineor.service.UserService;
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
