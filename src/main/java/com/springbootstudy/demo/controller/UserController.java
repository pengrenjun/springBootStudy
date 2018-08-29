package com.springbootstudy.demo.controller;

import com.springbootstudy.demo.entity.User;
import com.springbootstudy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @Description:   用户信息controller
 * @Author：pengrj
 * @Date : 2018/7/22 0022 15:04
 * @version:1.0
 */
@RestController
@RequestMapping("/user")
public class UserController  {



    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getUserById", method= RequestMethod.GET)
    public    User getProjectDescription(@RequestParam(value = "id" ) Integer id) throws Exception {
        User user=userService.getUserById(id);
        if(ObjectUtils.isEmpty(user)){
            throw new Exception("adf");
        }
        return user;

    }


    @DeleteMapping(value = "/deleteUserById")
    public void deleteUserById(@RequestParam(value = "id" ) Integer id){
         userService.deleteUserById(id);

    }

    @GetMapping(value = "/getUserByLocation")
    public List <User> getUserByLocation(@RequestParam(value = "location" ) String location){
        return userService.getAllUsersByLocation(location);

    }



    @GetMapping(value = "/getAllUsers")
    public List<User> userList(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getAllUsersByMyBatis")
    public List<User> getAllUsersByMyBatis(){
        return userService.getAllUsersByMybatis();
    }

    @Transactional
    @PostMapping(value = "/addorupateUser")
    public User  addorupateUser(@RequestParam(value = "id" ,required = false) Integer id,
                                @RequestParam(value = "name" ,required = false) String name,
                                @RequestParam(value = "location" ,required = false) String location,
                                @RequestParam(value = "comments" ,required = false) String comments,
                                @RequestParam(value = "birthday" ,required = false) Date birthday) {
        User user=new User();
        user.setId(id);
        user.setBirthday(birthday);
        user.setComments(comments);
        user.setLocation(location);
        user.setName(name);
        return userService.saveorupdateUser(user);
    }



}