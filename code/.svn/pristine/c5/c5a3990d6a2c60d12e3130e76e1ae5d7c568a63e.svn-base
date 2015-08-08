package data.strategydata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.StrategyPo;
import data.datafactory.DataFactory;
import dataservice.strategydataservice.StrategyDataService;

@SuppressWarnings("serial")
public class StrategyData extends UnicastRemoteObject implements StrategyDataService {
	
	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;

	public StrategyData() throws RemoteException {
		// TODO Auto-generated constructor stub
		con = null;
		sta = null;
		df = new DataFactory();
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<StrategyPo> find() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<StrategyPo> list = new ArrayList<StrategyPo>();
		try {
			
			//�õ�Strategy��Ϣ
			ResultSet set = sta.executeQuery("select * from Strategy");

			while(set.next()) {
				StrategyPo strategy = new StrategyPo(set.getString("module"), set.getString("category"), 
						set.getString("property"), set.getInt("maxCredit"), set.getInt("minCredit"), 
						set.getInt("maxTerm"), set.getInt("minTerm"));
				list.add(strategy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return list;
		}
	}

	@Override
	public void insert(StrategyPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.executeUpdate("insert into Strategy values('" + po.getModule() + "','" + po.getCategory() + "','" + 
		          po.getProperty() + "'," + po.getMaxCredit() + "," + po.getMinCredit() + "," + po.getMaxTerm() + 
		          "," + po.getMinTerm() + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}

	@Override
	public void update(StrategyPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.execute("update Strategy set property='" + po.getProperty() + "',maxCredit=" + po.getMaxCredit() + 
					",minCredit="+ po.getMinCredit() + ",maxTerm=" + po.getMaxTerm() + ",minTerm=" + po.getMinTerm() +
					" where module='" + po.getModule() + "' and category='" + po.getCategory() + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
}
