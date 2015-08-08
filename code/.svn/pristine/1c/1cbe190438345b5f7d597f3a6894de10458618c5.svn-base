package data.plandata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.PlanPo;
import data.datafactory.DataFactory;
import dataservice.plandataservice.PlanDataService;

@SuppressWarnings("serial")
public class PlanData extends UnicastRemoteObject implements PlanDataService {

	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;

	public PlanData() throws RemoteException {
		// TODO Auto-generated constructor stub
		con = null;
		sta = null;
		df = new DataFactory();
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<PlanPo> find(String department) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<PlanPo> list = new ArrayList<PlanPo>();
		try {
			ResultSet set = sta.executeQuery("select * from Plan where department='" + department + "'");

			while(set.next()) {
				PlanPo plan = new PlanPo(set.getString("module"), set.getString("category"), set.getString("property"), 
						set.getString("courseNo"), set.getString("courseName"), set.getString("department"), set.getInt("credit"), 
						set.getInt("weekHour"), set.getInt("term"));
				if (plan != null) {
					list.add(plan);
				}
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
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<PlanPo> find() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<PlanPo> list = new ArrayList<PlanPo>();
		try {
			ResultSet set = sta.executeQuery("select * from Plan");

			while(set.next()) {
				PlanPo plan = new PlanPo(set.getString("module"), set.getString("category"), set.getString("property"), 
						set.getString("courseNo"), set.getString("courseName"), set.getString("department"), set.getInt("credit"), 
						set.getInt("weekHour"), set.getInt("term"));
				if (plan != null) {
					list.add(plan);
				}
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
	public void insert(PlanPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.executeUpdate("insert into Plan values('" + po.getCourseNo() + "','" + po.getCourseName() + "','" + 
		            po.getModule() + "','" + po.getCategory() + "','" + po.getProperty() + "'," + po.getCredit() + "," + 
					po.getWeekHour() + ",'"+ po.getDepartment() + "'," + po.getTerm() + ")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}

	@Override
	public void update(PlanPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.executeUpdate("update Plan set module='" + po.getModule() + "',category='" + po.getCategory() + 
					"',property='" + po.getProperty() + "',credit=" + po.getCredit() + ",weekHour=" + po.getWeekHour() 
					+ ",term=" + po.getTerm() + " where courseNo='" + po.getCourseNo() + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
}
