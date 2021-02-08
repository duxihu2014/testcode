package com.yuandengta.mybatis.session;

import com.yuandengta.mybatis.mapping.MyConfiguration;
import com.yuandengta.mybatis.parsing.XMLConfigBuilder;

import java.io.InputStream;

public class MySqlSessionFactoryBuilder {
    public MySqlSessionFactory build(InputStream inputStream) {
        MyConfiguration myConfiguration = new XMLConfigBuilder(inputStream).parse();
        return new MySqlSessionFactory(myConfiguration);
    }
}
