import java.rmi.*;

public interface MyRMIInterface extends Remote{
    public int[][] getTranspose(int[][] mat,int r,int c) throws RemoteException;
}