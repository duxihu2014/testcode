package com.yuandengta.mybatis.mapping;

import java.util.Map;

public class MyConfiguration {

    //封装mybatis-config.xml
    private MyEnvironment myEnvironment;

    //封装XXXMapper.xml
    private Map<String,MappedStatement> mapperStatementMap;

    public MyEnvironment getMyEnvironment() {
        return myEnvironment;
    }

    public void setMyEnvironment(MyEnvironment myEnvironment) {
        this.myEnvironment = myEnvironment;
    }

    public Map<String, MappedStatement> getMapperStatementMap() {
        return mapperStatementMap;
    }

    public void setMapperStatementMap(Map<String, MappedStatement> mapperStatementMap) {
        this.mapperStatementMap = mapperStatementMap;
    }
}
