package sifangtestcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class StatementTest {
	public static void main(String[] args) {
		test_autoCommit();
	}
	public static void test_autoCommit()
        {
        String driver="oracle.jdbc.driver.OracleDriver";
        String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
        String user="user";
        String password="password";
        Connection conn=null;
        Statement stat=null;
        try {
            //1、注册驱动
            Class.forName(driver);
            //2、获取连接
             conn= DriverManager.getConnection(url, user, password);
             conn.setAutoCommit(false);
             //System.out.println(conn);
            //3、创建statement对象
             stat=conn.createStatement();
             //4、执行sql语句
             String sql="insert into tb_user values(22,'zhangsan',to_date('19-9-1999','dd-mm-yyyy'))"; //注意格式            
//           stat.execute(sql);
             System.out.println(stat.execute(sql)); //返回值为false，因为同样没有ResultSet返回集
             conn.commit();
             //5、处理结果集
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally{
            //6、关闭资源
            try {
                if(stat!=null)stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn!=null)conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}