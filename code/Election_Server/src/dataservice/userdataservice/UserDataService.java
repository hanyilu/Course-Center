package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPo;

public interface UserDataService extends Remote {

	/**
	 * 插入用户持久化对象
	 * @param po 用户持久化对象
	 * @return 插入结果
	 * @throws RemoteException
	 */
	public String insert(UserPo po) throws RemoteException;
	
	/**
	 * 删除用户持久化对象
	 * @param po 用户持久化对象
	 * @throws RemoteException
	 */
	public void delete(UserPo po) throws RemoteException;
	
	/**
	 * 删除已毕业的学生
	 * @param year 当前学年
	 * @throws RemoteException
	 */
	public void delete(int year) throws RemoteException;
		
	/**
	 * 更新用户持久化对象
	 * @param po 用户持久化对象
	 * @throws RemoteException
	 */
	public void update(UserPo po) throws RemoteException;
	
	/**
	 * 根据id返回用户信息
	 * @param id 用户名
	 * @return 特定用户信息
	 * @throws RemoteException
	 */
	public UserPo find(String id) throws RemoteException;
	
	/**
	 * 根据角色返回用户信息
	 * @param role 用户角色
	 * @return 某一角色的所有用户信息
	 * @throws RemoteException
	 */
	public ArrayList<UserPo> findUser(String role) throws RemoteException;
	
	/**
	 * 查找所有用户
	 * @return 所有用户信息
	 * @throws RemoteException
	 */
	public ArrayList<UserPo> findAll() throws RemoteException;
	
	/**
	 * 查找某一院系的某角色的所有用户
	 * @param de 院系
	 * @param role 角色
	 * @return 该院系角色为role的所有用户信息
	 * @throws RemoteException
	 */
	public ArrayList<UserPo> findDeUser(String de, String role) throws RemoteException;
	
	/**
	 * 查找某院系的所有用户
	 * @param de 院系
	 * @return 该院系的所有用户信息
	 * @throws RemoteException
	 */
	public ArrayList<UserPo> findDeUser(String de) throws RemoteException;
	
	//先移交该老师的课程数据然后将其删除
//	public void giveDelTeacher(String oldT, String newT) throws RemoteException;
}
