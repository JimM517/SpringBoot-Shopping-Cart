package com.shoppingcart.dao;

import com.shoppingcart.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getByUserId(int userId);

    int findIdByUsername(String username);

    User create(User user);

}
