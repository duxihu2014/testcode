package com.yuandengta.mybatis.pool;

import com.yuandengta.mybatis.mapping.MyEnvironment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class MyDataSource {
    //TODO:03待完善

    private MyEnvironment environment;
    private List<Connection> pool;
    private Connection conn = null;
    private static MyDataSource instance = null;
    private static final int POOL_SIZE = 15;

    public MyDataSource(MyEnvironment myEnvironment) {
        this.environment = myEnvironment;
        pool = new ArrayList<>(POOL_SIZE);
        this.createConnection();
    }

    /**
     * 创建初始的数据库连接
     */
    public void createConnection(){
        for(int i = 0;i < POOL_SIZE;i++){
            try {
                Class.forName(environment.getDriver());
                conn = DriverManager.getConnection(environment.getUrl(),environment.getUsername(),environment.getPassword());
                pool.add(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static DataSource getInstance(MyEnvironment myEnvironment) {
        if(instance ==null){
            instance = new MyDataSource(myEnvironment);
        }
        return null;
    }
}
