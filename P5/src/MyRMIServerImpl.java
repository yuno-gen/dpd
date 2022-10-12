import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyRMIServerImpl extends UnicastRemoteObject implements MyRMIInterface {

    static  jdbc_test jdbc = new jdbc_test();


    public MyRMIServerImpl() throws RemoteException {
        jdbc.connect();
        System.out.println("Creating server Object...");
    }

    public static void main(String args[]) {
        try {
            // create a server object
            MyRMIServerImpl myserver = new MyRMIServerImpl();

            // bind/register the server object (RMI registry)
            LocateRegistry.createRegistry(4001);
            Naming.rebind("rmi://localhost:4001/insert", myserver);

            LocateRegistry.createRegistry(3002);
            Naming.rebind("rmi://localhost:4001/update", myserver);

            LocateRegistry.createRegistry(3003);
            Naming.rebind("rmi://localhost:4001/delete", myserver);

            LocateRegistry.createRegistry(3004);
            Naming.rebind("rmi://localhost:4001/select", myserver);

            LocateRegistry.createRegistry(3005);
            Naming.rebind("rmi://localhost:4001/selectwithrno", myserver);

            System.out.println("Server Ready... \nprepare message sent");

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }

    @Override
    public String insert(int rno, String name, float cgpa) throws Exception {
        System.out.println("Inserting...");
//        System.out.println(name);
        String res = jdbc.insertVal(rno, name, cgpa);
//        System.out.println(res);
        if(res.equals("1")){
            System.out.println("Success");
            return "Insertion Successful";
        }
        System.out.println("Failed");
        return "Insertion failed";
    }

    @Override
    public String update(int rno, String name, float cgpa) throws Exception {
        System.out.println("Updating...");
//        TimeUnit.SECONDS.sleep(5);
        if(jdbc.updateVal(rno, name, cgpa).equals("1")){
            System.out.println("Success");

            return "Updation Successful";
        }
        System.out.println("Failed");

        return "Updation failed";
    }

    @Override
    public String delete(int rno) throws Exception {
        System.out.println("Deleting...");
//        TimeUnit.SECONDS.sleep(5);
        if(jdbc.delete(rno).equals("1")){
            System.out.println("Success");

            return "Deletion Successful";
        }
        System.out.println("Failed");

        return "Deletion failed";
    }

    @Override
    public ArrayList<String> display() throws Exception {
        ArrayList<String> arr= jdbc.displayVal();
        return arr;
    }

    @Override
    public ArrayList<String> selectwithrno(String rno) throws Exception {
        System.out.println("Waiting...");
//        TimeUnit.SECONDS.sleep(5);
        System.out.println("Success");

        return jdbc.selectwithrnoVal(rno);
    }
}