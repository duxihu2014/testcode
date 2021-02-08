package com.yuandengta.mybatis.executor;

import com.yuandengta.mybatis.mapping.MappedStatement;
import com.yuandengta.mybatis.mapping.MyConfiguration;
import com.yuandengta.mybatis.pool.MyDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MyExecutor {

    private DataSource dataSource;

    public MyExecutor(MyConfiguration myConfiguration) {
        dataSource = MyDataSource.getInstance(myConfiguration.getMyEnvironment());
    }


    public <T> List<T> query(MappedStatement mappedStatement, Object args) {
        List<T> resultList = new ArrayList<>();
        //TOOD:01---待完善
        return resultList;
    }

}
