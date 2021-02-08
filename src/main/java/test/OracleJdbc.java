package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public class OracleJdbc {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection conn = null;
	public static void main(String[] args) throws Exception {
//		String policyCode = "200408270156008";
//		//OracleJdbc.getDrawNameByDrawFlag(policyCode);
//		OracleJdbc.getNextDateByChangeId("4", 11123367L);
		OracleJdbc.getConnection();
//		OracleJdbc.getPolicyIdAndMaxDueTimeList("1,2,3");
		
		OracleJdbc.hasAuth("1", 11211, "1", "123", "11");
	}
	
	public static boolean hasAuth(String userType, long userId ,String AUTHORITY_TYPE, String BUSINESS_TYPE,String ACCIDENT_RESULT) throws Exception {
		boolean  V_RESULT =true;
		String accResults [] = ACCIDENT_RESULT.split(",");
		//DBean db = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String trueResults = "";
	    try {
	     // db = new DBean();
	     // db.connect();
	      StringBuffer sql = new StringBuffer();
	      
	      if("0".equals(userType))
			{
	    	  sql.append("SELECT a.accident_result FROM t_claim_outer_auth_new a WHERE a.user_type = 0 AND A.AUTH_TYPE = 0 AND A.QUALITY_GRADE_ID = " +
	    	  		"(SELECT T.QUALITY_GRADE_ID FROM T_AGENT T WHERE T.AGENT_ID = ?) AND A.BUSI_TYPE = ? "); 
			}else if("1".equals(userType))
			{
				 sql.append("SELECT a.accident_result FROM t_claim_outer_auth_new a WHERE a.user_type = 1 AND A.AUTH_TYPE = 0 AND A.BUSI_TYPE = ?"); 
			}
	      pst = OracleJdbc.getConnection().prepareStatement(sql.toString());
	      if("0".equals(userType))
			{
	    	  pst.setLong(1, userId);
	    	  pst.setString(2, BUSINESS_TYPE);
			}else if("1".equals(userType))
			{
				  pst.setString(1, BUSINESS_TYPE);
			}
	      rs = pst.executeQuery();
	      if(rs.next()){//
	    	  trueResults = rs.getString(1);
	  	      }
	          
	      for(String myAccResult:accResults)
	      {
	    	  if(trueResults.indexOf(myAccResult)<0)
	    	  {
	    		  V_RESULT =false;
	    		  break;
	    	  }
	      }
	    } catch (Exception ex) {
	      throw ex;
	    } finally {
	      
	    }
	    return V_RESULT;
	  }
	
	  public static List<Map<String,String>> getPolicyIdAndMaxDueTimeList(String applyIds) throws Exception {
			
			Connection con=null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			String sql = "";
			try {
				sql = " select t.policy_id,max(due_time) max_due_time from t_renew_rollback_apply t where t.status ='1' and t.apply_id in ( ?,?,? ) group by t.policy_id";
				pst = conn.prepareStatement(sql.toString());
				pst.setString(1, "12");
				pst.setInt(2, 2);
				pst.setInt(3, 3);
				rs = pst.executeQuery();
				while (rs.next()) {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			return null;
		}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn =DriverManager.getConnection("jdbc:oracle:thin:@10.1.101.35:1521:ORA35", "tpdev", "tpdevenvpwd");
//		String flag = "N";
//		String a[] ={"N","Y"};
//		for(String ai :a)
//		{
//			System.out.println("==============="+ai);
//			if(flag.equals(ai))
//			{
//				flag ="Y";
//			}
//			if(flag.equals("Y")){
//				break;
//			}
//		}
//		System.out.println(flag);
		return conn;
	}
	
	
	
	
	public static  String getNextDateByChangeId(String pay_type,long changeId)  {
	   
	    
	    PreparedStatement psmt = null;
	    ResultSet rs = null;
	    String sql = "";
	    String result = "";
	    try {
	      conn = getConnection();
	      if(pay_type!=null&&pay_type.equals("4"))
	      {
	    	  sql = "SELECT ADD_MONTHS(TO_DATE(to_char(tpc.validate_time,'yyyy')||'-'||to_char(tcm.validate_date,'MM')||'-'||to_char(tcm.validate_date,'DD'),'yyyy-MM-DD'),1) AS next_time " +
	    		" from t_contract_master tcm ,t_policy_change tpc " +
	    		"WHERE tpc.change_id= ? AND tcm.policy_id=tpc.policy_id ";
	      }else{
	    	 sql = "SELECT ADD_MONTHS(TO_DATE(to_char(tpc.validate_time,'yyyy')||'-'||to_char(tcm.validate_date,'MM')||'-'||to_char(tcm.validate_date,'DD'),'yyyy-MM-DD'),12) AS next_time " +
	  		" from t_contract_master tcm ,t_policy_change tpc " +
	  		" WHERE tpc.change_id= ? AND tcm.policy_id=tpc.policy_id ";
	      }
	      System.out.println("��һ�����������յ�����------sql -> " + sql);
	      System.out.println("changeId-------> " + changeId);
	      psmt = conn.prepareStatement(sql);
	      psmt.setLong(1, changeId);
	      rs = psmt.executeQuery();
	      if(rs.next()){
		    	result = (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(rs.getDate("next_time"));
		    }
	      System.out.println("��һ�����������յ�����------sql -> " + sql);
	      System.out.println("��һ�����������յ�����------result -> " + result);
	      
	      return result;
	    } catch (Exception e) {
	      //throw ExceptionFactory.parse(e);
	    	return result;
	    } 
	  }
	
	 public static String getDrawNameByDrawFlag(String policyCode) {
			//DBean db = new DBean();
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql=null;
			String drawName=null;
			try {
//				db.connect();
//				con = db.getConnection();
//				if(form.getDrawFlag()=="1"||form.getDrawFlag()=="2"){
//					if(form.getDrawFlag()=="1"){//Ͷ����
				//sql = "select c.real_name from t_contract_master  m,t_customer c where m.applicant_id = c.customer_id and m.policy_code =?";
					//}else{
						sql="select t.real_name from t_contract_master m,t_agent t where m.service_id =t.agent_id and m.policy_code =?";	
					//}
					//Log.debug("+++getDrawName+++ sql is:" + sql);
					pst = OracleJdbc.getConnection().prepareStatement(sql);
					//pst.setString(1, form.getPolicyCode());
					pst.setString(1, policyCode);
					rs = pst.executeQuery();
					while(rs.next()){
						drawName=rs.getString("real_name");
						System.out.println("Lirui----->"+drawName);
						//Log.debug("+++draw_name  is:" + rs.getString("draw_name"));
					}
					drawName="pop";
				
				//Log.debug("+++drawName+++ is:" + drawName);
				
				return drawName;
			} catch (Exception ex) {
//				Log.error(InvoicePrintInspectDAO.class, ex);
//				throw ExceptionFactory.parse(ex);
			} finally {
//				Tools.clear(rs, pst, db);
			}
			return drawName;
		}
		

}
