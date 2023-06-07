package com.shoppingcart.dao;

import com.shoppingcart.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao{

    // TODO: 6/6/2023 NEED TO MAKE UPDATES TO USER MODEL AND SECURITY //


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
    public User findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        for(User user : this.findAll()) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public User create(User newUser) {
       String sql = "INSERT INTO users (user_name, password_hash, role, name, address, city, state_code, zip) values (?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id";
       String password_hash = new BCryptPasswordEncoder().encode(newUser.getPassword());
       int userId = jdbcTemplate.queryForObject(sql, int.class, newUser.getUserName(), password_hash, newUser.getRole(), newUser.getName(), newUser.getAddress(), newUser.getCity(), newUser.getStateCode(), newUser.getZip());
       return getByUserId(userId);
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
