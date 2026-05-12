package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void add(User user) {
        userMapper.insert(user);
    }

    public void updateById(User user) {
        userMapper.updateById(user);
    }

    /**
     * 分页查询的方法
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize,String name){
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(name);
        return PageInfo.of(list);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);

    }
}
