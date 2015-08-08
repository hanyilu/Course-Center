package dataservice.plandataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PlanPo;

public interface PlanDataService extends Remote {
	
	/**
	 * 插入教学计划持久化对象
	 * @param po 教学计划持久化对象
	 * @throws RemoteException
	 */
	public void insert(PlanPo po) throws RemoteException;
		
	/**
	 * 更新教学计划持久化对象
	 * @param po 教学计划持久化对象
	 * @throws RemoteException
	 */
	public void update(PlanPo po) throws RemoteException;
	
	/**
	 * 得到院系教学计划信息
	 * @param department 院系
	 * @return 院系教学计划信息
	 * @throws RemoteException
	 */
	public ArrayList<PlanPo> find(String department) throws RemoteException;
	
	/**
	 * 得到教学计划信息
	 * @return 各个院系的教学计划信息
	 * @throws RemoteException
	 */
	public ArrayList<PlanPo> find() throws RemoteException;	
}
