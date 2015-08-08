package dataservice.coursedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CoursePo;

public interface CourseDataService extends Remote {

	/**
	 * 插入课程持久化对象
	 * @param po 课程持久化对象
	 * @return 插入结果
	 * @throws RemoteException
	 */
	public String insert(CoursePo po) throws RemoteException;
	
	/**
	 * 更新课程持久化对象
	 * @param po 课程持久化对象
	 * @throws RemoteException
	 */
	public void update(CoursePo po) throws RemoteException;
	
	/**
	 * 查找所有的课程信息
	 * @return 所有课程的信息
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findAll() throws RemoteException;
	
	/**
	 * 查找所有可选课程信息
	 * @return 所有可选课程信息
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findElection() throws RemoteException;
	
	/**
	 * 根据院系查找该院系已发布的课程
	 * @param department 院系
	 * @return 该院系的课程信息
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findJW(String department) throws RemoteException;
		
	/**
	 * 根据用户名查找任课老师的课程
	 * @param id 用户名
	 * @return 任课老师的课程信息
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findT(String id) throws RemoteException;
	
	/**
	 * 根据课程编号查找课程
	 * @param courseNo 课程号
	 * @return 特定课程信息
	 * @throws RemoteException
	 */
	public CoursePo findCourse(String courseNo) throws RemoteException;
	
	/**
	 * 查找某院系的所有必修课程信息
	 * @param department 院系
	 * @param term 学期
	 * @return 该院系的所有必修课程
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findDeCourse(String department, int term) throws RemoteException;
}
