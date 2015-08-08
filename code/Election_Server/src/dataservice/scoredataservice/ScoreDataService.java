package dataservice.scoredataservice;

import po.ScorePo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ScoreDataService extends Remote {

	/**
	 * 插入成绩持久化对象
	 * @param po 成绩持久化对象
	 * @throws RemoteException
	 */
	public void insert(ScorePo po) throws RemoteException;
	
	/**
	 * 更新成绩持久化对象
	 * @param po 成绩持久化对象
	 * @throws RemoteException
	 */
	public void update(ScorePo po) throws RemoteException;
	
	/**
	 * 学生根据用户名查找自己的成绩信息
	 * @param id 用户名
	 * @return 学生的成绩信息
	 * @throws RemoteException
	 */
	public ArrayList<ScorePo> findMyScore(String id) throws RemoteException;
	
	/**
	 * 得到某学生指定学期的成绩信息
	 * @param id 用户名
	 * @param term 学期
	 * @return 某学生指定学期的成绩信息
	 * @throws RemoteException
	 */
	public ArrayList<ScorePo> findMyScore(String id, int term) throws RemoteException;
	
	/**
	 * 教师根据课程编号查找课程的成绩信息
	 * @param courseNo 课程号
	 * @return 课程的成绩信息
	 * @throws RemoteException
	 */
	public ArrayList<ScorePo> findCourseScore(String courseNo) throws RemoteException;
	
	/**
	 * 教师根据课程编号查找课程的学生信息
	 * @param courseNo 课程号
	 * @return 课程的学生信息
	 * @throws RemoteException
	 */
	public ArrayList<ScorePo> findCourseStudent(String courseNo) throws RemoteException;
}
