package po;

import java.io.Serializable;

public class PlanPo implements Serializable {
	
	private static final long serialVersionUID = 003;

	private String module;        //课程模块
	private String category;      //课程分类
	private String property;      //课程性质
	private String courseNo;
	private String courseName;
	private String department;
	private int credit;
	private int weekHour;
	private int term;
	
	public PlanPo() {
		this("", "", "", "", "", "", 0, 0, 0);
	}
	
	public PlanPo(String mo, String ca, String pr, String no, String name, String de, int credits, int wh, int t) {
		module = mo;
		category = ca;
		property = pr;
		courseNo = no;
		courseName = name;
		department = de;
		this.credit = credits;
		this.weekHour = wh;
		term = t;
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
		
	public String getCourseNo() {
		return courseNo;
	}
		
	public String getCourseName() {
		return courseName;
	}
		
	public String getDepartment() {
		return department;
	}
		
	public int getCredit() {
		return credit;
	}
		
	public int getWeekHour() {
		return weekHour;
	}
	
	public int getTerm() {
		return term;
	}
}
