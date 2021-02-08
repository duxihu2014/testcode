package test;

import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();

		// ������package�е�AllTest suite
		suite.addTest(AllTests.suite());

		// ���뱾package level�е�Unit Test case
		//suite.addTestSuite(AllTests.class);

		return suite;
	}
}//EOC

