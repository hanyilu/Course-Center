package businesslogic.scorebl;

import vo.CourseVo;
import vo.ScoreVo;

import java.util.*;

import businesslogic.controlbl.Controller;
import businesslogicservice.scoreblservice.ScoreBLService;

public class ScoreController implements ScoreBLService {
	private Score s;
	private Controller con;
	
	public ScoreController(){
	    s = new Score();
	    con = new Controller();
	}
	
	@Override
	public ArrayList<CourseVo> getMyCourse(String id) {
		return s.getMyCourse(id);
	}
	
	@Override
	public ArrayList<ScoreVo> getStudents(String courseNo) {
		return s.getStudents(courseNo);
	}
	
	@Override
	public ArrayList<ScoreVo> showMyScore(String id) {
		return s.showMyScore(id);
	}

	@Override
	public ArrayList<ScoreVo> showMyScore(String id, int term) {
		// TODO Auto-generated method stub
		return s.showMyScore(id, term);
	}
	
	@Override
	public ArrayList<Integer> setScore(ArrayList<ScoreVo> scoList) {
		if (con.getState() == 6) {
			return s.setScore(scoList);
		}
		else {
			ArrayList<Integer> result = new ArrayList<Integer>();
			result.add(-1);
			return result;
		}
	}
}
