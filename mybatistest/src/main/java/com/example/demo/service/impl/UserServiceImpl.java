package com.example.demo.service.impl;

import com.example.demo.common.Page;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.List;

/**
 * Created by zyb on 2018/9/11.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User getNameById(User user) {
        return userDao.getNameById(user);
    }

    @Override
    public List<User> queryPage(Page user) {
        return userDao.queryPage(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
        reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }


}
