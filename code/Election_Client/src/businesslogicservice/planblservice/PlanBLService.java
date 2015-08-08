package businesslogicservice.planblservice;

import vo.PlanVo;
import java.util.*;

public interface PlanBLService {

	/**
	 * 得到某院系的教学计划
	 * @param department 院系
	 * @return 院系教学计划
	 */
	public ArrayList<PlanVo> getPlan(String department);
	
	/**
	 * 得到教学计划
	 * @return 各院系的教学计划
	 */
	public ArrayList<PlanVo> getAllPlan();
		
	/**
	 * 发布教学计划
	 * @param pv 教学计划信息
	 * @return 操作结果
	 */
	public String setPlan(PlanVo pv);
	
	/**
	 * 更新教学计划
	 * @param pv 教学计划信息
	 * @return 更新结果
	 */
	public String updatePlan(PlanVo pv);
}
