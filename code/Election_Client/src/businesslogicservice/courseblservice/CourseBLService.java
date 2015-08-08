package businesslogicservice.courseblservice;

import vo.*;

import java.util.ArrayList;

import businesslogic.coursebl.CourseTime;

public interface CourseBLService {

	/**
	 * 发布课程
	 * @param course 课程信息
	 * @return 操作结果
	 */
	public String publish(CourseVo course);
	
	/**
	 * 修改课程信息
	 * @param course 课程信息
	 * @return 操作结果
	 */
	public String modify(CourseVo course);
	
	/**
	 * 任课老师填写课程信息
	 * @param info 课程信息
	 * @return 填写结果
	 */
	public String addCourseInfo(CourseVo info);
	
	/**
	 * 得到所有课程的信息
	 * @return 所有课程的信息
	 */
	public ArrayList<CourseVo> getCourses();
	
	/**
	 * 得到特定的某一门课的信息
	 * @param courseNo 课程号
	 * @return 某门课的课程信息
	 */
	public CourseVo getCourse(String courseNo);
		
	/**
	 * 得到某院系已发布的课程
	 * @param department 院系
	 * @return 该院系的已发布课程
	 */
	public ArrayList<CourseVo> showJWCourse(String department);
	
	/**
	 * 得到任课老师的课程
	 * @param id
	 * @return 工号为id的用户的课程
	 */
	public ArrayList<CourseVo> showTCourse(String id);

	/**
	 * 检测两位任课老师的课程是否冲突
	 * @param tea1 任课老师1的id
	 * @param tea2 任课老师2的id
	 * @return 检测结果
	 */
	public String isConflict(String tea1, String tea2);

	/**
	 * 检测两个课程时间是否冲突
	 * @param time
	 * @param time1
	 * @return 检测结果
	 */
	public boolean checkTime(CourseTime[] time, CourseTime[] time1);

	/**
	 * 根据参数值得到课程时间表示
	 * @param time
	 * @return 课程时间表示
	 */
	public CourseTime[] getTime(String time);
	
	/**
	 * 根据课程号得到该门课程目前的学生人数
	 * @param courseNo 课程号
	 * @return 当前学生人数
	 */
	public int getCurStuNum(String courseNo);

	/**
	 * 根据课程号得到该门课程的最大学生人数
	 * @param courseNo 课程号
	 * @return 最大学生人数
	 */
	public int getMaxStuNum(String courseNo);

	/**
	 * 在选课阶段，得到该门课程的选课人数
	 * @param courseNo 课程号
	 * @return 选课人数
	 */
	public int getCurSelNum(String courseNo);
}
