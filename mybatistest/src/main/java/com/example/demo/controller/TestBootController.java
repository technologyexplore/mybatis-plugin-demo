package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zyb on 2018/9/11.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBootController {

    @Autowired
    private UserService userService;

    @RequestMapping("getuser")
    public User getUser() {
        User user = new User();
        user.setName("test");
        return user;
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(User user) {
        User u=userService.getNameById(user);
        return JSONObject.toJSONString(u);
    }

    @RequestMapping("/queryPage")
    @ResponseBody
    public String queryPage(com.example.demo.common.Page page) {
        if (null==page.getDbIndex()){
            page.setDbIndex(1);
        }
        if (null==page.getDbNumber()){
            page.setDbNumber(20);
        }
        List<User> u=userService.queryPage(page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",u);
        jsonObject.put("totalNum",page.getTotalNumber());
        return jsonObject.toJSONString();
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody User  user){
        user.setId(1);
        userService.update(user);
        return JSONObject.toJSONString(user);
    }

}
