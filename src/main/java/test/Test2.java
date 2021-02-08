package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Test2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Test2.checkModifyDateBeforeChangeDate(1L, new Date());
	}
	
	 public static boolean checkModifyDateBeforeChangeDate(long agentId,Date modifyDate) throws Exception {
			boolean flag = false;
			Date startDate = null;
			Date enterCompanyDate=null;
			//DBean db = new DBean();
			PreparedStatement pst = null;
			ResultSet rs = null;
			StringBuffer sql = new StringBuffer();
			try {
				
				
				if (1>1) {
					startDate=rs.getTimestamp(1);
				} else {
					//enterCompanyDate=getEnterCompanyDateByAgentId(agentId);
					if (enterCompanyDate != null) {
						startDate=enterCompanyDate;
					} else {
						startDate=new Date();
					}
				}
//				if (!modifyDate.before(startDate)) {
//					flag=true;
//				}
			} catch (Exception e) {
				//throw ExceptionFactory.parse(e);
			} finally {
				//Tools.clear(rs, pst, db);
			}
			return flag;
		}


}
