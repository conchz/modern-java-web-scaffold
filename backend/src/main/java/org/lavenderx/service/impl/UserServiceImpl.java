package org.lavenderx.service.impl;

import org.lavenderx.model.dao.UserDAO;
import org.lavenderx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
}
