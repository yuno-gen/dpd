import java.rmi.*;

public interface MyRMIInterface extends Remote {
    public String prepare1() throws RemoteException;

    public String prepare2() throws RemoteException;

    public String checkp1() throws RemoteException;

    public String checkp2() throws RemoteException, InterruptedException;

    public void sendreadyp1() throws RemoteException;

    public void sendabortp1() throws RemoteException;

    public String abort() throws RemoteException;

    public void ack() throws RemoteException;

    public void exit() throws RemoteException;
}