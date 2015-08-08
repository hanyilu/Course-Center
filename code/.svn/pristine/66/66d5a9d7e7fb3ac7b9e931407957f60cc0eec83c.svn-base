package po;

import java.io.Serializable;

public class ScorePo implements Serializable {
	
	private static final long serialVersionUID = 002;

	private double score;
	private double gpa;
	private int comCre;		     //必修课学分总数
	private int optCre;			 //选修课学分总数
	private int credits;		 //学分总数
	private boolean access;      //准入准出课程统计
	private CoursePo course;
	private String studentID;
		
	public ScorePo(double s, double g, int cc, int oc, int cr, boolean a, CoursePo c, String u) {
		score = s;
		gpa = g;
		comCre = cc;
		optCre = oc;
		credits = cr;
		access = a;
		course = c;
		studentID = u;
	}
	
	public double getScore() {
		return score;
	}
		
	public double getGpa() {
		return gpa;
	}
		
	public int getComCre() {
		return comCre;
	}

	public int getOptCre() {
		return optCre;
	}

	public int getCredits() {
		return credits;
	}

	public boolean getAccess() {
		return access;
	}
			
	public String getStudent() {
		return studentID;
	}
		
	public CoursePo getCourse() {
		return course;
	}
}
