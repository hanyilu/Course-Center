package vo;

import po.ElectionPo;

public class ElectionVo {

	private CourseVo course;
	private String studentID;
	private int registerYear;
		
	public ElectionVo (CourseVo c, String s, int g){
		course = c;
		studentID = s;
		registerYear = g;
	}
	
	public ElectionVo(ElectionPo po) {
		course = new CourseVo(po.getCourse());
		studentID = po.getStudent();
		registerYear = po.getRegisterYear();
	}
	
	public CourseVo getCourse(){
		return course;
	}
		
	public String getStudent(){
		return studentID;
	}
	
	public int getRegisterYear() {
		return registerYear;
	}
}
