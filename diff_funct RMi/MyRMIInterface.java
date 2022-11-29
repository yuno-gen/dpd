import java.rmi.*;

public interface MyRMIInterface extends Remote 
{
    //declare functions here
    public String reverse(String string) throws RemoteException;
} 