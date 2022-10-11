import java.rmi.*;

public interface MyRMIInterface extends Remote {
    // declare functions here
    public String prepare() throws RemoteException;

    public String receive_abort() throws RemoteException, InterruptedException;

    public void receive_ready(int num) throws RemoteException, InterruptedException;

    public String commit() throws RemoteException;

    public String abort() throws RemoteException;

    public String message() throws RemoteException;

    public void ack() throws RemoteException;

}