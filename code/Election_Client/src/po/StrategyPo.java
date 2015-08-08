package po;

import java.io.Serializable;

import vo.StrategyVo;

public class StrategyPo implements Serializable {

	private static final long serialVersionUID = 005;
	
	private String module;        //课程模块
	private String category;      //课程分类
	private String property;      //课程性质
	private int maxCredit;
	private int minCredit;
	private int maxTerm;
	private int minTerm;
		
	public StrategyPo(String mo, String ca, String pro, int maxC, int minC,int maxT,int minT) {
		module = mo;
		category = ca;
		property = pro;
		maxCredit = maxC;
		minCredit = minC;
		maxTerm = maxT;
		minTerm = minT;
	}
	
	public StrategyPo(StrategyVo vo) {
		module = vo.getModule();
		category = vo.getCategory();
		property = vo.getProperty();
		maxCredit = vo.getMaxCredit();
		minCredit = vo.getMinCredit();
		maxTerm = vo.getMaxTerm();
		minTerm = vo.getMinTerm();
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
