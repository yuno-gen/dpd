import java.rmi.*;
import java.rmi.server.*;
import java.net.MalformedURLException;
import java.sql.*;

public class MyRMIServerImpl extends UnicastRemoteObject implements MyRMIInterface
{
	public MyRMIServerImpl() throws RemoteException 
	{
		System.out.println("Creating server Object ...");
	}

	//demote methode for string reverse
	public String reverse(String string) throws RemoteException
	{
		String rev="";
		char ch[]=string.toCharArray();  
    	for(int i=ch.length-1;i>=0;i--)
		{  
        	rev+=ch[i];  
    	}  
    	return rev;  
	}

	public static void main(String args[]) 
	{
		try
		{
			//createing server object	
			MyRMIServerImpl myserver = new MyRMIServerImpl();
			Naming.rebind("Sayandeep",myserver);
			System.out.println("Server Ready...");
		}
		catch(Exception ex) 
		{
			System.out.println("Error " +ex);
		}
	} 
}