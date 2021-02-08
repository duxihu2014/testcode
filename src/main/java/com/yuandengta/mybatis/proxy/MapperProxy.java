package com.yuandengta.mybatis.proxy;

import com.yuandengta.mybatis.session.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collector;

public class MapperProxy implements InvocationHandler {
    private MySqlSession mySqlSession;

    public MapperProxy(MySqlSession mySqlSession) {
        this.mySqlSession = mySqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> clazz = method.getReturnType();
        if(Collection.class.isAssignableFrom(clazz)){
            //mySqlSession.selectList(args);
        }else if(Map.class.isAssignableFrom(clazz)){
            //mySqlSession.selectMap(args)
        }else{
            String statementKey = method.getDeclaringClass().getName()+"."+method.getName();
            return mySqlSession.selectOne(statementKey,null == args?null:args[0]);
        }
        return null;
    }
}
