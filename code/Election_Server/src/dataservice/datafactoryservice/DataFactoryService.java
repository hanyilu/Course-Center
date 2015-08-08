package dataservice.datafactoryservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dataservice.admindataservice.AdminDataService;
import dataservice.controldataservice.ControllerDataService;
import dataservice.coursedataservice.CourseDataService;
import dataservice.electiondataservice.ElectionDataService;
import dataservice.plandataservice.PlanDataService;
import dataservice.scoredataservice.ScoreDataService;
import dataservice.strategydataservice.StrategyDataService;
import dataservice.userdataservice.UserDataService;

public interface DataFactoryService extends Remote {
	
	/**
	 * 得到管理员数据库服务的引用
	 * @return 管理员数据库服务的引用
	 * @throws RemoteException
	 */
	public AdminDataService getAdminDatabase() throws RemoteException;
	
	/**
	 * 得到课程数据库服务的引用
	 * @return 课程数据库服务的引用
	 * @throws RemoteException
	 */
	public CourseDataService getCourseDatabase() throws RemoteException;
	
	/**
	 * 得到选课数据库服务的引用
	 * @return 选课数据库服务的引用
	 * @throws RemoteException
	 */
	public ElectionDataService getElectionDatabase() throws RemoteException;
	
	/**
	 * 得到教学计划数据库服务的引用
	 * @return 教学计划数据库服务的引用
	 * @throws RemoteException
	 */
	public PlanDataService getPlanDatabase() throws RemoteException;
	
	/**
	 * 得到成绩数据库服务的引用
	 * @return 成绩数据库服务的引用
	 * @throws RemoteException
	 */
	public ScoreDataService getScoreDatabase() throws RemoteException;
	
	/**
	 * 得到整体框架策略数据库服务的引用
	 * @return 整体框架策略数据库服务的引用
	 * @throws RemoteException
	 */
	public StrategyDataService getStrategyDatabase() throws RemoteException;
	
	/**
	 * 得到用户数据库服务的引用
	 * @return 用户数据库服务的引用
	 * @throws RemoteException
	 */
	public UserDataService getUserDatabase() throws RemoteException;
	
	/**
	 * 得到控制器数据库服务的引用
	 * @return 控制器数据库服务的引用
	 * @throws RemoteException
	 */
	public ControllerDataService getControllerDatabase() throws RemoteException;
}
