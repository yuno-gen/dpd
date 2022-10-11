import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.concurrent.TimeUnit;

public class MyRMIServerImpl extends UnicastRemoteObject implements MyRMIInterface {


    public MyRMIServerImpl() throws RemoteException {
        System.out.println("Creating server Object...");
    }

    public static void main(String args[]) {
        try {
            // create a server object
            MyRMIServerImpl myserver = new MyRMIServerImpl();

            // bind/register the server object (RMI registry)
            // Naming.rebind("rmi://localhost:5001/Chinmay",myserver);
            LocateRegistry.createRegistry(3001);
            Naming.rebind("rmi://localhost:3000/insert", myserver);
            
            LocateRegistry.createRegistry(3002);
            Naming.rebind("rmi://localhost:3000/update", myserver);

            LocateRegistry.createRegistry(3003);
            Naming.rebind("rmi://localhost:3000/delete", myserver);

            LocateRegistry.createRegistry(3004);
            Naming.rebind("rmi://localhost:3000/select", myserver);

            LocateRegistry.createRegistry(3005);
            Naming.rebind("rmi://localhost:3000/selectwithrno", myserver);

            System.out.println("Server Ready... \nprepare message sent");

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }

    @Override
    public String insert(int rno, String name, float cgpa) throws Exception {
        System.out.println("Inserting...");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Insertion Successful");
        return null;
    }

    @Override
    public String update(int rno, String name, float cgpa) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String delete(int rno) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String display() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String selectwithrno(int rno) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}