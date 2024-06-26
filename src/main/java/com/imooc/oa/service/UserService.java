package com.imooc.oa.service;

import com.imooc.oa.entity.User;
import com.imooc.oa.mapper.UserMapper;
import com.imooc.oa.service.exception.LoginException;
import com.imooc.oa.utils.Md5Utils;

public class UserService {
    private UserMapper userMapper = new UserMapper();

    public User checkLogin(String username, String password){
        User user = userMapper.selectByUsername(username);
        if (user == null){
            throw new LoginException("用户名不存在");
        }
        String md5 = Md5Utils.md5Digest(password, user.getSalt());
        if(!md5.equals(user.getPassword())){
            throw new LoginException("密码错误");
        }
        return user;
    }
}
