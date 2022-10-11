import java.rmi.*;

public interface MyRMIInterface extends Remote {
    // declare functions here
    public int convertPoundsToRupees(int pounds) throws RemoteException;

    public String getUserName(String uid) throws RemoteException;
    // Receives a number and returns square
    // - Receives purchase amount and returns discount
    public int square(int n) throws RemoteException;
    public int discount(int purchaseAmt) throws RemoteException;
}