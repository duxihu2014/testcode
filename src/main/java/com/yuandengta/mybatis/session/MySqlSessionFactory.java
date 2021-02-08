package com.yuandengta.mybatis.session;

import com.yuandengta.mybatis.executor.MyExecutor;
import com.yuandengta.mybatis.mapping.MyConfiguration;

public class MySqlSessionFactory {

    private MyConfiguration myConfiguration;

    public MySqlSessionFactory(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public MySqlSession openSession() {
        MyExecutor myExecutor = new MyExecutor(myConfiguration);
        return new MySqlSession(myConfiguration,myExecutor);
    }


}
