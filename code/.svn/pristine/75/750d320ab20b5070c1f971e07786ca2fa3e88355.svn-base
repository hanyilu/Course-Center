package vo;

import po.StrategyPo;

public class StrategyVo {

	private String module;        //课程模块
	private String category;      //课程分类
	private String property;      //课程性质
	private int maxCredit;
	private int minCredit;
	private int maxTerm;
	private int minTerm;
		
	public StrategyVo(String mo, String ca, String pro, int maxC, int minC, int maxT,int minT) {
		module = mo;
		category = ca;
		property = pro;
		maxCredit = maxC;
		minCredit = minC;
		maxTerm = maxT;
		minTerm = minT;
	}
	
	public StrategyVo(StrategyPo po) {
		module = po.getModule();
		category = po.getCategory();
		property = po.getProperty();
		maxCredit = po.getMaxCredit();
		minCredit = po.getMinCredit();
		maxTerm = po.getMaxTerm();
		minTerm = po.getMinTerm();
	}
	
	public String getModule() {
		return module;
	}
		
	public String getCategory() {
		return category;
	}
		
	public String getProperty() {
		return property;
	}
		
	public int getMaxCredit() {
		return maxCredit;
	}
		
	public int getMinCredit() {
		return minCredit;
	}
	
	public int getMaxTerm() {
		return maxTerm;
	}

	public int getMinTerm() {
		return minTerm;
	}
}
