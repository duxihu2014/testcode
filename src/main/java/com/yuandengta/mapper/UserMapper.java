package com.yuandengta.mapper;

import kaikeba.po.User;

import java.util.List;

public interface UserMapper {
    public User selectById(Integer id);
    public List<User> selectAll();
}
