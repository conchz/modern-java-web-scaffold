package com.github.lavenderx.service.impl;

import com.github.lavenderx.service.UserService;
import com.github.lavenderx.model.dao.UserDAO;
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
