package businesslogic.coursebl;

import java.util.ArrayList;

import vo.CourseVo;
import businesslogic.controlbl.Controller;
import businesslogicservice.courseblservice.CourseBLService;

public class CourseController implements CourseBLService{
	private Course c;
	private Controller con;
	
	public CourseController(){
		c = new Course();
		con = new Controller();
	}

	@Override
	public String publish(CourseVo course) {
		// TODO Auto-generated method stub
		if(con.getState()==2){	//发布课程
			
			/*
			 * 判断是否为当前学期课程
			 */
			if(con.getTerm()==(course.getPlan().getTerm()%2))
				return c.publish(course);
			return "不是当前学期课程";
		}
		else
			return "当前阶段不可发布课程，如有疑问请咨询管理员";
	}
	
	@Override
	public String modify(CourseVo course) {
		// TODO Auto-generated method stub
		
		//发布课程阶段才可修改课程，一旦进入选课阶段就不能修改
		if (con.getState() == 2) {
			return c.modify(course);
		}
		else
			return "当前阶段不可修改课程信息，如有疑问请咨询管理员";
	}

	@Override
	public String addCourseInfo(CourseVo info) {
		// TODO Auto-generated method stub
		return c.addCourseInfo(info);
	}
	
	@Override
	public ArrayList<CourseVo> getCourses() {
		// TODO Auto-generated method stub
		return c.getCourses();
	}
	
	public CourseVo getCourse(String courseNo) {
		// TODO Auto-generated method stub
		return c.getCourse(courseNo);
	}
	
	@Override
	public ArrayList<CourseVo> showJWCourse(String id) {
		// TODO Auto-generated method stub
		return c.showJWCourse(id);
	}

	@Override
	public ArrayList<CourseVo> showTCourse(String id) {
		// TODO Auto-generated method stub
		return c.showTCourse(id);
	}

	@Override
	public String isConflict(String tea1, String tea2) {
		// TODO Auto-generated method stub
		return c.isConflict(tea1, tea2);
	}

	@Override
	public boolean checkTime(CourseTime[] time, CourseTime[] time1) {
		// TODO Auto-generated method stub
		return c.checkTime(time, time1);
	}

	@Override
	public CourseTime[] getTime(String time) {
		// TODO Auto-generated method stub
		return c.getTime(time);
	}

	@Override
	public int getCurStuNum(String courseNo) {
		// TODO Auto-generated method stub
		return c.getCurStuNum(courseNo);
	}

	@Override
	public int getMaxStuNum(String courseNo) {
		// TODO Auto-generated method stub
		return c.getMaxStuNum(courseNo);
	}

	@Override
	public int getCurSelNum(String courseNo) {
		// TODO Auto-generated method stub
		return c.getCurSelNum(courseNo);
	}
}
