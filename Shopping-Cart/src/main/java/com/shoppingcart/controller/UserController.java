package com.shoppingcart.controller;

import com.shoppingcart.dao.JdbcUserDao;
import com.shoppingcart.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final JdbcUserDao jdbcUserDao;

    public UserController(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    @GetMapping
    public List<User> getUsers() {
        return jdbcUserDao.findAll();
    }

}
