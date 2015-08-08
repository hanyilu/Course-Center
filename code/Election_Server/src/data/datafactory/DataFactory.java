package data.datafactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

import data.admindata.AdminData;
import data.controldata.ControllerData;
import data.coursedata.CourseData;
import data.electiondata.ElectionData;
import data.plandata.PlanData;
import data.scoredata.ScoreData;
import data.strategydata.StrategyData;
import data.userdata.UserData;
import dataservice.admindataservice.AdminDataService;
import dataservice.controldataservice.ControllerDataService;
import dataservice.coursedataservice.CourseDataService;
import dataservice.datafactoryservice.DataFactoryService;
import dataservice.electiondataservice.ElectionDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.scoredataservice.ScoreDataService;
import dataservice.strategydataservice.StrategyDataService;
import dataservice.userdataservice.UserDataService;

@SuppressWarnings("serial")
public class DataFactory extends UnicastRemoteObject implements DataFactoryService {
	private Connection con;
	private Statement sta;

	public DataFactory() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/jwSystem?useUnicode=true&characterEncoding=utf8",
					"admin","admin");
			sta = con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public Statement getStatement() {
		return sta;
	}
	
	public void closeConnection() {
		try {
			if (sta != null) {
				sta.close();
				sta = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public AdminDataService getAdminDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new AdminData();
	}

	@Override
	public CourseDataService getCourseDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new CourseData();
	}

	@Override
	public ElectionDataService getElectionDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new ElectionData();
	}

	@Override
	public PlanDataService getPlanDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new PlanData();
	}

	@Override
	public ScoreDataService getScoreDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new ScoreData();
	}

	@Override
	public StrategyDataService getStrategyDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new StrategyData();
	}
	
	@Override
	public ControllerDataService getControllerDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new ControllerData();
	}

	@Override
	public UserDataService getUserDatabase() throws RemoteException {
		// TODO Auto-generated method stub
		return new UserData();
	}
	
	public int boolToInt(boolean op) {
		if (op)
			return 1;
		else
			return 0;
	}
	
	public boolean intToBool(int op) {
		if (op == 1)
			return true;
		else
			return false;
	}
}
