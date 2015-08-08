package po;

import java.io.Serializable;

import vo.CourseVo;

public class CoursePo implements Serializable{

	private static final long serialVersionUID = 006;
	
	private PlanPo info;		    //课程模块，课程分类、课程性质，各课程编号，各课程名称，各课程学分，各学期周学时分配
	private String teacherID;		//任课老师
	private String time;			//上课时间
	private boolean optional;		//是否必修选修
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
	
	public CoursePo(CourseVo vo) {
		info = new PlanPo(vo.getPlan());
		teacherID = vo.getTeacher();
		time = vo.getTime();
		optional = vo.isOptional();
		textbook = vo.getTextbook();
		referbook = vo.getReferbook();
		outline = vo.getOutline();
		studentNum = vo.getStudentNum();
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
