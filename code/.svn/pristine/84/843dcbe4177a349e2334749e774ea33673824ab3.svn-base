package businesslogic.electionbl;

import java.util.ArrayList;

import vo.*;
import businesslogic.controlbl.Controller;
import businesslogicservice.electionblservice.ElectionBLService;

public class ElectionController implements ElectionBLService {
	
	private Election ele;
	private Controller con;
	
	public ElectionController() {
		ele = new Election();
		con = new Controller();
	}
	
	@Override
	public String addCourse(ArrayList<ElectionVo> election) {
		// TODO Auto-generated method stub
		/*
		 * 选课，对暂时的表进行操作
		 */
		if(con.getState()==3){
			return ele.addCourse(election);
		}
		/*
		 * 补选，直接添加到最终的选课信息中
		 */
		if(con.getState()==4){
			return ele.add(election);
		}
		return "当前不能进行选课和补选";
	}

	@Override
	public String deleteCourse(ArrayList<ElectionVo> ele) {
		// TODO Auto-generated method stub
		if (con.getState() == 4) {
			return this.ele.deleteCourse(ele);
		}
		else
			return "当前不能退选";
	}

	@Override
	public ArrayList<CourseVo> getAvilCourse() {
		// TODO Auto-generated method stub
		return ele.getAvilCourse();
	}
	
	@Override
	public ArrayList<CourseVo> getComCourse() {
		// TODO Auto-generated method stub
		return ele.getComCourse();
	}

	@Override
	public ArrayList<ElectionVo> getElection(String id) {
		// TODO Auto-generated method stub
		return ele.getElection(id);
	}

	@Override
	public ArrayList<UserVo> getCouElection(String courseNo) {
		// TODO Auto-generated method stub
		ArrayList<UserVo> user = ele.getCouElection(courseNo);
		con.adjustGrade(user);
		
		return user;
	}

	@Override
	public ArrayList<CourseVo> getMyCourse(String id) {
		// TODO Auto-generated method stub
		return ele.getMyCourse(id);
	}

	@Override
	public ArrayList<ElectionVo> getTemEle(String id) {
		// TODO Auto-generated method stub
		return ele.getTemEle(id);
	}

	@Override
	public void decideEle() {
		// TODO Auto-generated method stub
		ele.decideEle();
	}
}
