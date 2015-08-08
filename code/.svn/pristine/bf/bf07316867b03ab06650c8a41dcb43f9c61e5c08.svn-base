package rmi;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import data.datafactory.DataFactory;
import dataservice.datafactoryservice.DataFactoryService;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try { 
	            //创建一个远程对象 
	            DataFactoryService service = new DataFactory(); 
	            //本地主机上的远程对象注册表Registry的实例，并指定端口为8888，这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上 
	           // DataFactoryService  stub =
	             //       (DataFactoryService) UnicastRemoteObject.exportObject(service);
	            LocateRegistry.createRegistry(8888); 

	            //把远程对象注册到RMI注册服务器上，并命名为Service 
	            //绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略，下面两种写法都是正确的） 
	           // Naming.bind("//localhost:8888/Service",service); 
	            Naming.bind("//localhost:8888/Service",service); 
	            
	            System.out.println(">>>>>INFO:远程Service对象绑定成功！"); 
	        } catch (RemoteException e) { 
	            System.out.println("创建远程对象发生异常！"); 
	            e.printStackTrace(); 
	        } catch (AlreadyBoundException e) { 
	            System.out.println("发生重复绑定对象异常！"); 
	            e.printStackTrace(); 
	        } catch (MalformedURLException e) { 
	            System.out.println("发生URL畸形异常！"); 
	            e.printStackTrace(); 
	        }
	}
}
