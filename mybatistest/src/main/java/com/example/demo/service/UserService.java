package com.example.demo.service;

import com.example.demo.common.Page;
import com.example.demo.entity.User;

import java.util.List;

/**
 * Created by zyb on 2018/9/11.
 */
public interface UserService {

    public User getNameById(User user);

    List<User> queryPage(Page user);

    void update(User user);
}
