package dataservice.admindataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.AdminPo;

public interface AdminDataService extends Remote {
	
	/**
	 * 查找管理员得到其信息
	 * @return 管理员密码
	 * @throws RemoteException
	 */
	public String find() throws RemoteException;
	
	/**
	 * 更新管理员信息
	 * @param po 管理员信息
	 * @throws RemoteException
	 */
	public void update(AdminPo po) throws RemoteException;
}
