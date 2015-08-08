package vo;

import po.CoursePo;

public class CourseVo {

	private PlanVo info;		    //课程模块，课程分类、课程性质，各课程编号，各课程名称，各课程学分，各学期周学时分配
	private String teacherID;		//任课老师
	private String time;			//上课时间
	private boolean optional;		//是否必修选修
	private String textbook;
	private String referbook;
	private String outline;
	private int studentNum; 		//若是选修课则指定上课人数
	
	public CourseVo() {
		this(new PlanVo(), "", "", false, "", "", "", 0);
	}
	
	public CourseVo(PlanVo p,String t,String s,boolean b, String text, String refer, String out, int num){
		info = p;
		teacherID = t;
		time = s;
		optional = b;
		textbook = text;
		referbook = refer;
		outline = out;
		studentNum = num;
	}
	
	public CourseVo(CoursePo po) {
		info = new PlanVo(po.getPlan());
		teacherID = po.getTeacher();
		time = po.getTime();
		optional = po.isOptional();
		textbook = po.getTextbook();
		referbook = po.getReferbook();
		outline = po.getOutline();
		studentNum = po.getStudentNum();
	}
	
	public PlanVo getPlan(){
		return info;
	}
	
	public void setPlan(PlanVo p) {
		info = p;
	}
		
	public String getTeacher(){
		return teacherID;
	}
		
	public String getTime(){
		return time;
	}
	
	public void setTime(String s){
		time = s;
	}
	
	public boolean isOptional(){
		return optional;
	}
		
	public String getTextbook() {
		return textbook;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	public String getReferbook() {
		return referbook;
	}

	public void setReferbook(String referbook) {
		this.referbook = referbook;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}
	
	public int getStudentNum() {
		return studentNum;
	}
}
