import java.rmi.*;
import java.rmi.server.*;

public class MyRMIServerImpl extends UnicastRemoteObject implements MyRMIInterface {
    public MyRMIServerImpl() throws RemoteException{
        System.out.println("Creating server objectW...");
    }

    public int[][] getTranspose(int[][] mat,int r,int c) throws RemoteException{
        int[][] result = new int[c][r];
        for(int i=0;i<c;i++){
            for(int j=0;j<r;j++){
                result[i][j] = mat[j][i];
            }
        }
        System.out.println("transpose returned...");
        return result;
    }

    public static void main(String args[]) throws Exception{

        try {
            MyRMIServerImpl server = new MyRMIServerImpl();
            Naming.rebind("dpd",server);
        System.out.println("Server Ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
