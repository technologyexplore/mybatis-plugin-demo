package com.example.demo.dao;

import com.example.demo.entity.Page;
import com.example.demo.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zyb on 2018/9/11.
 */
@Repository
public interface UserDao {

    public User getNameById(User user);

    public List<User> queryPage(com.example.demo.common.Page user);

    Page<User> pageByConditions(RowBounds bounds, User user);

    void update(User user);
}
