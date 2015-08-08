package dataservice.strategydataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.StrategyPo;

public interface StrategyDataService extends Remote {

	/**
	 * 插入整体框架策略持久化对象
	 * @param po 整体框架策略持久化对象
	 * @throws RemoteException
	 */
	public void insert(StrategyPo po) throws RemoteException;
	
	/**
	 * 更新整体框架策略持久化对象
	 * @param po 整体框架策略持久化对象
	 * @throws RemoteException
	 */
	public void update(StrategyPo po) throws RemoteException;
	
	/**
	 * 得到整体框架策略信息
	 * @return 整体框架策略信息
	 * @throws RemoteException
	 */
	public ArrayList<StrategyPo> find() throws RemoteException;
}
