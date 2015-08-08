package vo;

import po.ScorePo;

public class ScoreVo {

	private double score;
	private double gpa;
	private int comCre;		     //必修课学分总数
	private int optCre;			 //选修课学分总数
	private int credits;		 //学分总数
	private boolean access;      //准入准出课程统计
	private String studentID;
	private CourseVo course;
		
	public ScoreVo(double s, double g, int cc, int oc, int cr, boolean a, CourseVo c, String u) {
		score = s;
		gpa = g;
		comCre = cc;
		optCre = oc;
		credits = cr;
		access = a;
		course = c;
		studentID = u;
	}
	
	public ScoreVo(ScorePo po) {
		score = po.getScore();
		gpa = po.getGpa();
		comCre = po.getComCre();
		optCre = po.getOptCre();
		credits = po.getCredits();
		access = po.getAccess();
		course = new CourseVo(po.getCourse());
		studentID = po.getStudent();
	}
	
	public double getScore() {
		return score;
	}
		
	public double getGpa() {
		return gpa;
	}
	
	public void setGpa(double newG) {
		gpa = newG;
	}
	
	public int getComCre() {
		return comCre;
	}

	public void setComCre(int comCre) {
		this.comCre = comCre;
	}

	public int getOptCre() {
		return optCre;
	}
	
	public void setOptCre(int optCre) {
		this.optCre = optCre;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public boolean getAccess() {
		return access;
	}
		
	public CourseVo getCourse() {
		return course;
	}
		
	public String getStudent() {
		return studentID;
	}
}
