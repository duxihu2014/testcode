package kaikeba;


import kaikeba.po.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class MyBatisV1 {

    private final String prefixStr = "db.sql.";
    private final String suffixStr = ".resulttype";

    private Properties properties = new Properties();

    public static void main(String[] args) throws SQLException {
        MyBatisV1 myBatisV1 = new MyBatisV1();
        myBatisV1.handle();
    }

    public void handle() throws SQLException {
        //1.step 1 加载properties文件
        loadPropertis("jdbc.properties");
        //step 2
        Integer param1 = 3;
        List<User> userList1 = selectList("queryUserById", param1);
        System.out.println("userList1--->" + userList1);

        String param2 = "bbb";
        List<User> userList2 = selectList("queryUserByName", param2);
        System.out.println("userList2--->" + userList2);

        Map<String, Object> param3 = new HashMap<>();
        param3.put("username", "aaa");
        param3.put("sex", "male");
        List<User> userList3 = selectList("queryUserByParams", param3);
        System.out.println("userList3--->" + userList3);
    }

    private void loadPropertis(String path) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> List<T> selectList(String statementId, Object param) throws SQLException {
        List<T> userList = new ArrayList<T>();
        Connection connection = getConnection();
        String sql = properties.getProperty(prefixStr + statementId);
        PreparedStatement st = connection.prepareStatement(sql);

        if (param instanceof Integer || param instanceof String) {
            st.setObject(1, param);
        } else {
            String columnnames = properties.getProperty(prefixStr + statementId + ".columnnames");
            String[] columnArrays = columnnames.split(",");
            Map<String, Object> datamap = (Map<String, Object>) param;
            for (int i = 0; i < columnArrays.length; i++) {
                st.setObject(i + 1, datamap.get(columnArrays[i]));
            }
        }


        ResultSet rs = st.executeQuery();
        String resulttype = properties.getProperty(prefixStr + statementId + suffixStr);
        Constructor<?> constructor = null;
        Class<?> cls = null;
        try {
            cls = Class.forName(resulttype);
            try {
                constructor = cls.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object result = null;
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        while (rs.next()) {
            try {
                result = constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i + 1);
                try {
                    Field field = cls.getDeclaredField(columnName);
                    field.setAccessible(true);
                    try {
                        field.set(result, rs.getObject(columnName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            userList.add((T) result);
        }
        return userList;
    }

    private Connection getConnection() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setDriverClassName(properties.getProperty("db.driver"));
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
