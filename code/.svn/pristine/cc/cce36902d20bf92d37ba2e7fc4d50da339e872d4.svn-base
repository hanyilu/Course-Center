package businesslogic.strategybl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import dataservice.datafactoryservice.DataFactoryService;
import po.StrategyPo;
import rmi.Client;
import vo.StrategyVo;

public class Strategy {

	private DataFactoryService service;
	private Client client;
	
	public Strategy() {
		client = new Client();
		service = client.getFactory();
	}

	public ArrayList<StrategyVo> getStrategy() {
		// TODO Auto-generated method stub
		try{
			ArrayList<StrategyPo> strategy = service.getStrategyDatabase().find();
			ArrayList<StrategyVo> s = new ArrayList<StrategyVo>();
			for(int i = 0; i < strategy.size(); i++) {
				s.add(new StrategyVo(strategy.get(i)));
			}
			return s;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String setStrategy(StrategyVo info) {
		// TODO Auto-generated method stub
		StrategyPo strategy = new StrategyPo(info);
		String check = checkStrategy(strategy, 0);
		if(check.equals("right")||check.equals("k_right")){
			try {
				service.getStrategyDatabase().insert(strategy);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
		}
		else {
			return check;
		}
		
		if(check.equals("k_right")){
			return "k_success";
		}
		return "success";
	}
	
	public String updateStrategy(StrategyVo info) {
		// TODO Auto-generated method stub
		StrategyPo strategy = new StrategyPo(info);
		
		try {
			service.getStrategyDatabase().update(strategy);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	/**
	 * 检测框架策略的合理性
	 * @param strategy 框架策略信息
	 * @param type 为0表示添加，为1表示修改
	 * @return 检测结果
	 */
	private String checkStrategy(StrategyPo strategy, int type) {
		//空
		if (strategy == null) {
			return "null";
		}
		if(strategy.getModule() == null || strategy.getProperty() == null||strategy.getCategory() == null){
			return "module_null";
		}
		
		//核对
		try {
			ArrayList<StrategyPo> s = service.getStrategyDatabase().find();
			for(int i = 0; i < s.size(); i++) {
				if(s.get(i).getModule().equals(strategy.getModule()) && 
						(s.get(i).getCategory().equals(strategy.getCategory()))){
					if(type == 0){
						return "exist";
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}	
		
		if(iskong(strategy)){
			return "k_right";
		}
		return "right";
	}

	private boolean iskong(StrategyPo strategy) {
		// TODO Auto-generated method stub
		if(strategy.getMinCredit() == 0 || strategy.getMaxCredit() == 0){
			return true;
		}
		return false;
	}
}