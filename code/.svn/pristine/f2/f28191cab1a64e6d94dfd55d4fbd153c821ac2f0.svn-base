package po;

import java.io.Serializable;

public class CoursePo implements Serializable{

	private static final long serialVersionUID = 006;
	
	private PlanPo info;		    //教学计划
	private String teacherID;		//任课老师
	private String time;			//上课时间
	private boolean optional;		//必修选修
	private String textbook;
	private String referbook;
	private String outline;
	private int studentNum;
	
	public CoursePo() {
		this(new PlanPo(), "", "", false, "", "", "", 0);
	}
	
	public CoursePo(PlanPo p, String t,String s,boolean b, String text, String refer, String out, int num){
		info = p;
		teacherID = t;
		time = s;
		optional = b;
		textbook = text;
		referbook = refer;
		outline = out;
		studentNum = num;
	}
	
	public PlanPo getPlan(){
		return info;
	}
	
	public String getTeacher(){
		return teacherID;
	}
		
	public String getTime(){
		return time;
	}
	
	public boolean isOptional(){
		return optional;
	}
	
	public String getTextbook() {
		// TODO Auto-generated method stub
		return textbook;
	}
	
	public String getReferbook(){
		return referbook;
	}

	public String getOutline() {
		return outline;
	}

	public int getStudentNum() {
		return studentNum;
	}
}
