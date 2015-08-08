package businesslogic.planbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import dataservice.datafactoryservice.DataFactoryService;
import po.PlanPo;
import rmi.Client;
import vo.PlanVo;

public class Plan {

	private DataFactoryService service;
	private Client client;
	
	public Plan() {
		client = new Client();
		service = client.getFactory();
	}
	
	//根据院系查看教学计划
	public ArrayList<PlanVo> getPlan(String department){
		try{
			ArrayList<PlanPo> plan = service.getPlanDatabase().find(department);
			ArrayList<PlanVo> planlist = new ArrayList<PlanVo>();
			for(int i = 0; i < plan.size(); i++) {
				planlist.add(new PlanVo(plan.get(i)));
			}
			return planlist;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//得到所有
	public ArrayList<PlanVo> getAllPlan() {
		// TODO Auto-generated method stub
		try{
			ArrayList<PlanPo> plan = service.getPlanDatabase().find();
			ArrayList<PlanVo> planlist = new ArrayList<PlanVo>();
			for(int i = 0; i < plan.size(); i++) {
				planlist.add(new PlanVo(plan.get(i)));
			}
			return planlist;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String setPlan(PlanVo pv) {
		// TODO Auto-generated method stub
		PlanPo plan = new PlanPo(pv);
		String check = checkPlan(plan,0);
		if(check.equals("right")){
			try {
				service.getPlanDatabase().insert(plan);
				return "发布成功";
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "啊哦~有错误~";
			}
		}
		else {
			return check;
		}
	}

	public String updatePlan(PlanVo pv) {
		// TODO Auto-generated method stub
		PlanPo plan = new PlanPo(pv);
		String check = checkPlan(plan,1);
		if(check.equals("right")){
			try {
				service.getPlanDatabase().update(plan);
				return "修改成功";
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "啊哦~有错误~";
			}
		}
		else {
			return check;
		}
	}
	
	/**
	 * 检测教学计划的合理性
	 * @param plan 教学计划信息
	 * @param type 为0表示添加，为1表示修改
	 * @return 检测结果
	 */
	private String checkPlan(PlanPo plan, int type) {
		//空
		if (plan == null) {
			return "请填写完整信息再提交";
		}

		//核对
		try {
			ArrayList<PlanPo> planlist = service.getPlanDatabase().find();
			for(int i = 0; i < planlist.size(); i++) {
				if(planlist.get(i).getCourseNo().equals(plan.getCourseNo())){
					if(type == 0){
						return "课程号已存在";
					}
					if(type == 1){
						if(!planlist.get(i).getCourseName().equals(plan.getCourseName())){
							return "课程名称不能改变";
						}
					}
				}
				else if(planlist.get(i).getCourseName().equals(plan.getCourseName())){
					return "课程名已存在";
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "啊哦~有错误~";
		}
		
		return "right";
	}
}
