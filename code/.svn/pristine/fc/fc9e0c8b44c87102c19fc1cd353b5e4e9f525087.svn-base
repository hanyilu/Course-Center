package data.controldata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import po.ControlPo;
import data.datafactory.DataFactory;
import dataservice.controldataservice.ControllerDataService;

@SuppressWarnings("serial")
public class ControllerData extends UnicastRemoteObject implements ControllerDataService{

	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;
	
	public ControllerData() throws RemoteException {
		con = null;
		sta = null;
		df = new DataFactory();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("finally")
	@Override
	public ControlPo find() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ControlPo po = null;
		try {
			ResultSet set = sta.executeQuery("select * from Controller");
			while(set.next()){
				po = new ControlPo(set.getInt("flag"),set.getInt("state"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return po;
		}
	}

	@Override
	public void update(ControlPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.execute("update Controller set flag=" + po.getFlag() + ",state=" +po.getState());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
}
