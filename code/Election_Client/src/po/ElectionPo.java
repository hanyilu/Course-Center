package po;

import java.io.Serializable;

import vo.ElectionVo;

public class ElectionPo implements Serializable {
	
	private static final long serialVersionUID = 004;

	private CoursePo course;
	private String studentID;
	private int registerYear;
		
	public ElectionPo (CoursePo c,String u, int g){
		course = c;
		studentID = u;
		registerYear = g;
	}
	
	public ElectionPo(ElectionVo vo) {
		course = new CoursePo(vo.getCourse());
		studentID = vo.getStudent();
		registerYear = vo.getRegisterYear();
	}
	
	public CoursePo getCourse(){
		return course;
	}
	
	public String getStudent(){
		return studentID;
	}
	
	public int getRegisterYear() {
		return registerYear;
	}
}
