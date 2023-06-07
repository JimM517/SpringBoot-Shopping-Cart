package com.shoppingcart.dao;

import com.shoppingcart.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User getByUserId(int userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            user = mapRowToUser(results);
        }
        return user;
    }

    @Override
    public int findIdByUsername(String username) {
        return 0;
    }

    @Override
    public User create(User user) {
        return null;
    }


    private User mapRowToUser(SqlRowSet results) {
        User user = new User();
        user.setId(results.getInt("user_id"));
        user.setUserName(results.getString("username"));
        user.setPasswordHash(results.getString("password_hash"));
        user.setRole(results.getString("role"));
        user.setName(results.getString("name"));
        user.setAddress(results.getString("address"));
        user.setCity(results.getString("city"));
        user.setStateCode(results.getString("state_code"));
        user.setZip(results.getString("zip"));
        return user;
    }
}
