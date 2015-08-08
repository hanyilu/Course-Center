package businesslogic.planbl;

import java.util.ArrayList;

import vo.PlanVo;
import businesslogic.controlbl.Controller;
import businesslogicservice.planblservice.PlanBLService;

public class PlanController implements PlanBLService{
	
	private Plan plan;
	private Controller con;
	
	public PlanController(){
		plan = new Plan();
		con = new Controller();
	}

	@Override
	public ArrayList<PlanVo> getPlan(String department) {
		// TODO Auto-generated method stub
		return plan.getPlan(department);
	}
	
	@Override
	public ArrayList<PlanVo> getAllPlan() {
		// TODO Auto-generated method stub
		return plan.getAllPlan();
	}

	@Override
	public String setPlan(PlanVo pv) {
		// TODO Auto-generated method stub
		if(con.getState()==1){
			return plan.setPlan(pv);
		}
		return "当前阶段不可发布教学计划，如有疑问请咨询管理员";
	}

	@Override
	public String updatePlan(PlanVo pv) {
		// TODO Auto-generated method stub
		if(con.getState()==1){
			return plan.updatePlan(pv);
		}
		return "当前阶段不可修改教学计划，如有疑问请咨询管理员";
	}
}
