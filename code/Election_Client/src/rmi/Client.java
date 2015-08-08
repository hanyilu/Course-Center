package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dataservice.datafactoryservice.DataFactoryService;

public class Client {
	
	public Client(){
		
	}
	
	public DataFactoryService getFactory(){
		DataFactoryService service = null;
		try {
			service = (DataFactoryService)Naming.lookup("//localhost:8888/Service");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service;
	}
}
