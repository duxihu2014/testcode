package test;





import org.junit.Before;
import org.junit.Test;




public class CityManagerTest {
	//CityManager cityManager = null;

	@Before
	public void manager() {
		//cityManager = (CityManager) SpringBeanFactory.getBean("cityManager");
		System.out.println("before__test.....");
	}

	
	//public void addAccount( Account account )
	//当然包括成功增加一个用户（在数据库中插入一条纪录）
	//还包括如果已经由一个相同的用户，应该返回一个用户已存在的消息

	//插入用户
	@Test
	public void addAccountTest() {
		System.out.println("test.....");
		
	}
	
	//检查用户是否存在
	@Test
	public void checkAccountTest() {
		System.out.println("test.....");
	}
	
	//Test Case 3: 传入的Account对象为NULL
	
}
