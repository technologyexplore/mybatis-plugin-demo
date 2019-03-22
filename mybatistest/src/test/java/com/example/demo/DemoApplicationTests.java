package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserDao userDao;
	@Test
	public void contextLoads() {
//		String resource = "conf.xml";
//
//		InputStream is = Test1.class.getClassLoader().getResourceAsStream(resource);
//
//		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
//
//		SqlSession session = sessionFactory.openSession(
	}

}
