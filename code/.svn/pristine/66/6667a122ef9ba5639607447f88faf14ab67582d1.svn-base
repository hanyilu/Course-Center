package dataservice.controldataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.ControlPo;

public interface ControllerDataService extends Remote {
	
	/**
	 * 得到系统当前状态
	 * @return 系统状态
	 * @throws RemoteException
	 */
	public ControlPo find() throws RemoteException;
	
	/**
	 * 更新系统状态
	 * @param po 系统状态
	 * @throws RemoteException
	 */
	public void update(ControlPo po) throws RemoteException;
}
