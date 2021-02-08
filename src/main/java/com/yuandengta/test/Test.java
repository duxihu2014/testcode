package com.yuandengta.test;

import com.yuandengta.mapper.UserMapper;
import com.yuandengta.mybatis.session.MySqlSession;
import com.yuandengta.mybatis.session.MySqlSessionFactory;
import com.yuandengta.mybatis.session.MySqlSessionFactoryBuilder;
import kaikeba.po.User;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        //构建SqlSessionFactory
        MySqlSessionFactory mySqlSessionFactory = new MySqlSessionFactoryBuilder().build(inputStream);
        //打开session
        MySqlSession mySqlSession = mySqlSessionFactory.openSession();
        //获取mapper对象
        UserMapper mapper = mySqlSession.getMapper(UserMapper.class);
        //调用mapper接口对象的方法操作数据库
        User user = mapper.selectById(1);
        System.out.println(user);
    }

}
