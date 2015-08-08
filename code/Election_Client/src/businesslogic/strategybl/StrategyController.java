package businesslogic.strategybl;

import java.util.ArrayList;

import vo.StrategyVo;
import businesslogic.controlbl.Controller;
import businesslogicservice.strategyblservice.StrategyBLService;

public class StrategyController implements StrategyBLService{
	
	private Strategy strategy;
	private Controller con;

	public StrategyController() {
		// TODO Auto-generated constructor stub
		strategy = new Strategy();
		con = new Controller();
	}

	@Override
	public ArrayList<StrategyVo> getStrategy() {
		// TODO Auto-generated method stub
		return strategy.getStrategy();
	}

	@Override
	public String updateStrategy(StrategyVo info) {
		// TODO Auto-generated method stub
		if(con.getState()==0){
			return strategy.updateStrategy(info);
		}
		return "当前阶段不可更新框架策略，如有疑问请咨询管理员";
	}

	@Override
	public String setStrategy(StrategyVo info) {
		// TODO Auto-generated method stub
		if(con.getState()==0)
			return strategy.setStrategy(info);
		return "当前阶段不可发布框架策略，如有疑问请咨询管理员";
	}
}
