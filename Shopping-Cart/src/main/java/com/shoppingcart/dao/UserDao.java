package com.shoppingcart.dao;

import com.shoppingcart.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getByUserId(int userId);

    User findByUsername(String username);

    User create(User newUser);

}
