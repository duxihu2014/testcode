package com.yuandengta.mybatis.session;

import com.yuandengta.mapper.UserMapper;
import com.yuandengta.mybatis.executor.MyExecutor;
import com.yuandengta.mybatis.mapping.MappedStatement;
import com.yuandengta.mybatis.mapping.MyConfiguration;
import com.yuandengta.mybatis.proxy.MapperProxy;

import java.lang.reflect.Proxy;
import java.util.List;

public class MySqlSession {

    private MyConfiguration myConfiguration;
    private MyExecutor myExecutor;

    public MySqlSession(MyConfiguration myConfiguration, MyExecutor myExecutor) {
        this.myConfiguration = myConfiguration;
        this.myExecutor = myExecutor;
    }

    public <T> T getMapper(Class<T> clazz) {
        MapperProxy mapperProxy = new MapperProxy(this);
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},mapperProxy);
    }


    public <T> T selectOne(String statementKey, Object args) {
        MappedStatement mappedStatement = myConfiguration.getMapperStatementMap().get(statementKey);
        List<T> resultList = myExecutor.query(mappedStatement,args);
        if(null !=resultList && resultList.size()>1){
            throw  new RuntimeException("more than one result");
        }else{
            return resultList.get(0);
        }
    }

}
