package businesslogicservice.electionblservice;

import java.util.ArrayList;

import vo.*;

public interface ElectionBLService {

	/**
	 * 选课阶段得到所有可选的课程
	 * @return 所有可选课程信息
	 */
	public ArrayList<CourseVo> getAvilCourse();
	
	/**
	 * 补选阶段得到可以补选的课程
	 * @return 可补选的课程信息
	 */
	public ArrayList<CourseVo> getComCourse();
	
	/**
	 * 学生得到自己的选课信息（选课阶段结束后的）
	 * @param id
	 * @return 选课信息
	 */
	public ArrayList<ElectionVo> getElection(String id);
	
	/**
	 * 学生根据id得到自己的选课信息（暂时的，选课阶段结束前的）
	 * @param id 
	 * @return 暂时的选课信息
	 */
	public ArrayList<ElectionVo> getTemEle(String id);
	
	/**
	 * 任课老师根据课程编号查找该课程的选课信息
	 * @param courseNo 课程号
	 * @return 课程的学生信息
	 */
	public ArrayList<UserVo> getCouElection(String courseNo);
	
	/**
	 * 学生根据自己的id查找自己的课程列表
	 * @param id
	 * @return 学生的课程列表
	 */
	public ArrayList<CourseVo> getMyCourse(String id);
	
	/**
	 * 提交选择的课程
	 * @param election 选择的课程
	 * @return 操作结果
	 */
	public String addCourse(ArrayList<ElectionVo> election);
	
	/**
	 * 退选已选择的课程
	 * @param ele 退选信息
	 * @return 操作结果
	 */
	public String deleteCourse(ArrayList<ElectionVo> ele);

	/**
	 * 选课算法
	 */
	public void decideEle();
}
