package businesslogicservice.scoreblservice;

import java.util.ArrayList;
import vo.*;


public interface ScoreBLService {

	/**
	 * 得到任课老师的课程列表
	 * @param id
	 * @return 工号为id的任课老师的课程信息
	 */
	public ArrayList<CourseVo> getMyCourse(String id);
	
	/**
	 * 得到课程的学生成绩列表
	 * @param courseNo 课程号
	 * @return 课程的学生成绩信息
	 */
	public ArrayList<ScoreVo> getStudents(String courseNo);
	
	/**
	 * 学生得到自己的成绩信息
	 * @param id
	 * @return 账号为id的学生的成绩信息
	 */
	public ArrayList<ScoreVo> showMyScore(String id);
	
	/**
	 * 学生得到自己某特定学期的成绩信息
	 * @param id 账号
	 * @param term 学期
	 * @return 账号为id的学生term学期的成绩信息
	 */
	public ArrayList<ScoreVo> showMyScore(String id, int term);
	
	/**
	 * 登记成绩
	 * @param scoList 要登记的成绩信息
	 * @return 成绩中的不合理项的下标集合，若无不合理项则返回null
	 */
	public ArrayList<Integer> setScore(ArrayList<ScoreVo> scoList);
}
