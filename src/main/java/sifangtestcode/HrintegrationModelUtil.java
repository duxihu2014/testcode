//package sifangtestcode;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.dom4j.DocumentException;
//import org.drools.core.util.StringUtils;
//
//import ext.sfauto.model.PLM_HR_INTEGRATIONUTIL;
//import ext.sfauto.pidim.syscong.bean.DepartMentInfoBean;
//import ext.sfauto.pidim.syscong.bpm.BPMIntegrationUtil;
//import ext.sfauto.pidim.syscong.util.DatabaseUtil;
//import wt.fc.PersistenceHelper;
//import wt.fc.QueryResult;
//import wt.log4j.LogR;
//import wt.query.QuerySpec;
//import wt.query.SearchCondition;
//import wt.session.SessionContext;
//import wt.session.SessionServerHelper;
//import wt.util.WTException;
//import wt.util.WTPropertyVetoException;
//
///**
// * @Author:liyy
// * @Date:2021/2/22 16:45
// * @Description:和HR集成工具类
// */
//public class HrintegrationModelUtil {
//    private static final String CLASSNAME = HrintegrationModelUtil.class.getName();
//    private static final Logger logger = LogR.getLogger(CLASSNAME);
//
//
//    /**
//     * 同步HR系统中的数据
//     *
//     * @throws DocumentException
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     */
//    public static void synchHrintegration() throws Exception {
//        System.out.println(">>>>>ynchHrintegration start");
//        //1.获取HR系统中所需要的数据
//        truncatteTable();
//        findAllHrBean();
//
//    }
//
//
//    /**
//     * 获取所有用户的
//     *
//     * @return
//     * @throws DocumentException
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     * @throws WTPropertyVetoException
//     */
//    public static void findAllHrBean() throws SQLException, ClassNotFoundException, DocumentException, WTException, IOException, WTPropertyVetoException {
//        logger.debug(">>>>>findAllHrBean start");
//
//        Connection bpmconnection = null;
//        try {
//            bpmconnection = DatabaseUtil.getBPMConn();
//            bpmconnection.setAutoCommit(false);
//            PreparedStatement pstm = null;
//            String sql = "SELECT t.NTAccount,t.DisplayName,t.DepartmentName, t.Company from Employee_View_Aris t";
//            logger.debug("sql==>" + sql);
//            pstm = bpmconnection.prepareStatement(sql);
//            ResultSet result = pstm.executeQuery();
//            while (result.next()) {
//                String userId = result.getString("NTAccount"); //用户Id
//                String displayName = result.getString("DisplayName"); //用户显示名称
//                String company = result.getString("company");//公司
//                //根据用户名称查询研发组以及部门经理
//                DepartMentInfoBean departMentInfoBean = BPMIntegrationUtil.getDataFromBPMByName(userId, bpmconnection);
//                String manger = departMentInfoBean.getManager(); //部门经理
//                String group = departMentInfoBean.getGroup(); //研发组
//                String departmentName = departMentInfoBean.getDepartment();//部门
//                if(StringUtils.isEmpty(departmentName)){
//                	departmentName = "null";
//                }
//                if(StringUtils.isEmpty(group)){
//                	group = "null";
//                }
//                PLM_HR_INTEGRATIONUTIL rintergrationBean = new PLM_HR_INTEGRATIONUTIL();
//                rintergrationBean.setCompany(company);
//                rintergrationBean.setDepartMentManager(manger);
//                rintergrationBean.setDepartMentName(departmentName);
//                rintergrationBean.setUserID(userId);
//                rintergrationBean.setUserName(displayName);
//                rintergrationBean.setResearchGroup(group);
//                PersistenceHelper.manager.save(rintergrationBean);
//                System.out.println(rintergrationBean.toString());
//            }
//
//        }finally {
//            if (bpmconnection != null) {
//                bpmconnection.commit();
//                bpmconnection.close();
//            }
//        }
//
//    }
//
//
//    /**
//     * 清空数据库表结构
//     */
//    public static void truncatteTable() throws Exception {
//    	QuerySpec qs = new QuerySpec(PLM_HR_INTEGRATIONUTIL.class);
//    	QueryResult qr = PersistenceHelper.manager.find(qs);
//    	while(qr.hasMoreElements()){
//    		Object obj = qr.nextElement();
//    		if(obj instanceof PLM_HR_INTEGRATIONUTIL){
//    			PLM_HR_INTEGRATIONUTIL phi = (PLM_HR_INTEGRATIONUTIL)obj;
//    			PersistenceHelper.manager.delete(phi);
//    		}
//    	}
//
//    }
//
//    /**
//     * 根据部门名称获取部门经理
//     *
//     * @param departmentName
//     * @return
//     * @throws WTException
//     */
//    public static String getDepartmentmanage(String departmentName) {
//    	//组织架构上，一个部门仅有一个部门经理（故中间表中可能会查询出多条数据，均为一人）
//    	String departmentManger = "";
//    	try {
//    		QuerySpec qs = new QuerySpec(PLM_HR_INTEGRATIONUTIL.class);
//        	qs.appendWhere(new SearchCondition(PLM_HR_INTEGRATIONUTIL.class, PLM_HR_INTEGRATIONUTIL.DEPART_MENT_NAME,
//    				SearchCondition.EQUAL,departmentName),  new int[]{0});
//        	QueryResult qr = PersistenceHelper.manager.find(qs);
//        	while(qr.hasMoreElements()){
//        		PLM_HR_INTEGRATIONUTIL phi = (PLM_HR_INTEGRATIONUTIL)qr.nextElement();
//        		departmentManger = phi.getDepartMentManager();
//        		if(org.apache.commons.lang.StringUtils.isNotEmpty(departmentManger) || !"null".equals(departmentManger)){
//        			break;
//        		}
//        	}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return departmentManger;
//
//    }
//
//
//    /**
//     * 根据员工信息获取员工所在部门以及公司
//     *
//     * @param userId
//     * @return
//     */
//    public static Map<String, String> getCompanyAndDepartment(String userId) {
//        logger.debug(">>>>>>getCompanyAndDepartment  start: userId" + userId);
//        Map<String,String> result = new HashMap<String,String>();
//        String department = "";
//        String company="";
//        // 忽略权限
//        boolean bool = SessionServerHelper.manager.isAccessEnforced();
//        SessionContext context = null;
//        try {
//            SessionServerHelper.manager.setAccessEnforced(false);
//            context = SessionContext.newContext();
//    		QuerySpec qs = new QuerySpec(PLM_HR_INTEGRATIONUTIL.class);
//        	qs.appendWhere(new SearchCondition(PLM_HR_INTEGRATIONUTIL.class, PLM_HR_INTEGRATIONUTIL.USER_ID,
//    				SearchCondition.EQUAL,userId),  new int[]{0});
//        	logger.debug(">>>>qs :" + qs.toString());
//        	QueryResult qr = PersistenceHelper.manager.find(qs);
//        	while(qr.hasMoreElements()){
//        		PLM_HR_INTEGRATIONUTIL phi = (PLM_HR_INTEGRATIONUTIL)qr.nextElement();
//        		company = phi.getCompany();
//        		department = phi.getDepartMentName();
//        		logger.debug(">>>>company :" + company);
//        		logger.debug(">>>>department :" + department);
//                result.put("COMPANY",company);
//                result.put("DEPARTMENT",department);
//        		if(org.apache.commons.lang.StringUtils.isNotEmpty(company) || !"null".equals(company)){
//        			if(org.apache.commons.lang.StringUtils.isNotEmpty(department) || !"null".equals(department)){
//            			break;
//        			}
//        		}
//        	}
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            SessionContext.setContext(context);
//            SessionServerHelper.manager.setAccessEnforced(bool);
//        }
//
//
//        return result;
//    }
//}
//
//
