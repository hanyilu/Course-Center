package businesslogicservice.controlblservice;

import java.util.ArrayList;

import vo.ControlVo;
import vo.UserVo;

public interface ControllerBLService {
	
	/**
	 * 管理员更改当前状态
	 * @param c 新的状态
	 * @return 操作结果
	 */
	public String change(ControlVo c);
	
	/**
	 * 管理员调整状态进入下一阶段
	 */
	public void advance();

	/**
	 * 管理员调整状态回到上一阶段
	 */
	public void back();
	
	/**
	 * 控制器得到当前学期
	 * @return 当前学期，0为下学期， 1为上学期
	 */
	public int getTerm();
	
	/**
	 * 控制器得到当前学年，2013表示2012~2013学年
	 * @return 当前学年，结果-学生注册年份得到学生当前年级
	 */
	public int getYear();
	
	/**
	 * 控制器得到当前状态
	 * 0-框架策略
	 * 1-发布教学计划
	 * 2-发布课程
	 * 3-选课
	 * 4-补选退选
	 * 5-只能查看
	 * 6-登记成绩
	 * 7-假期，可以调整用户
	 * @return 当前状态，判断情况 
	 */
	public int getState();

	/**
	 * 管理员得到当前进度
	 * @return 包括学年学期及当前进程的String形式
	 */
	public String getCurrent();
	
	/**
	 * 根据系统状态得到学生的年级信息
	 * @param register 学生的注册年份
	 * @return 学生的年级
	 */
	public int getGrade(int register);
	
	/**
	 * 根据用户信息和当前系统所处学期判断用户的年级
	 * @param user 用户信息
	 */
	public void adjustGrade(ArrayList<UserVo> user);
	
	/**
	 * 根据用户信息和当前系统所处学期判断用户的年级
	 * @param user 用户信息
	 */
	public void adjustGrade(UserVo user);
}
