package data.admindata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import po.AdminPo;
import data.datafactory.DataFactory;
import dataservice.admindataservice.AdminDataService;

@SuppressWarnings("serial")
public class AdminData extends UnicastRemoteObject implements AdminDataService {
	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;

	public AdminData() throws RemoteException {
		con = null;
		sta = null;
		df = new DataFactory();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("finally")
	@Override
	public String find() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		String p=null;
		try {
			ResultSet set = sta.executeQuery("select * from Admin");
			while(set.next())
				p =set.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return p;
		}
	}

	@Override
	public void update(AdminPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.execute("update Admin set password='" + po.getPassword()+"' where id='admin'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
}
