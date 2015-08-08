package dataservice.electiondataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CoursePo;
import po.ElectionPo;
import po.UserPo;

public interface ElectionDataService extends Remote {

	/**
	 * 学生选课
	 * @param po 选课信息
	 * @throws RemoteException
	 */
	public void insertTemp(ElectionPo po) throws RemoteException;
	
	/**
	 * 院系教务老师在发布必修课程时为本院学生添加课程信息
	 * @param po 必修课程信息
	 * @throws RemoteException
	 */
	public void insert(ElectionPo po) throws RemoteException;
	
	/**
	 * 学生退选
	 * @param po 退选信息
	 * @throws RemoteException
	 */
	public void delete(ElectionPo po) throws RemoteException;
	
	/**
	 * 学生根据id和是否选修得到自己的课程信息
	 * @param id 学生id
	 * @param o 必修选修信息，1代表选修，0代表必修
	 * @return 学生的必修或选修课程信息
	 * @throws RemoteException
	 */
	public ArrayList<ElectionPo> findMine(String id,int o) throws RemoteException;
	
	/**
	 * 根据课程编号查找课程的选课信息
	 * @param courseNo 课程编号
	 * @return 课程的选课信息
	 * @throws RemoteException
	 */
	public ArrayList<UserPo> find(String courseNo) throws RemoteException;
	
	/**
	 * 得到所有可选的课程信息
	 * @return 可选课程信息
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findAll() throws RemoteException;
		
	/**
	 * 学生根据id得到自己的课程信息（包括必修和选修）
	 * @param id 学生id
	 * @return 学生的必修和选修课程信息
	 * @throws RemoteException
	 */
	public ArrayList<CoursePo> findMyCourse(String id) throws RemoteException;
	
	/**
	 * 分配选课时得到所有学生之前的选课信息
	 * @return 所有学生的选课信息
	 * @throws RemoteException
	 */
	public ArrayList<ElectionPo> findAllElection() throws RemoteException;
	
	/**
	 * 选课分配完成后将暂时存储选课信息的表清空
	 */
	public void clearTemEle() throws RemoteException;

	/**
	 * 根据id得到暂时的选课信息
	 * @param id
	 * @return 暂时的选课信息
	 * @throws RemoteException
	 */
	public ArrayList<ElectionPo> findTempEle(String id) throws RemoteException;
	
	/**
	 * 选课阶段得到选某门课的学生的信息
	 * @param courseNo 课程号
	 * @return 学生信息
	 * @throws RemoteException
	 */
	public ArrayList<UserPo> findTempStu(String courseNo) throws RemoteException;
}
