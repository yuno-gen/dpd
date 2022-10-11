import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.concurrent.TimeUnit;

public class MyRMIServerImpl extends UnicastRemoteObject implements MyRMIInterface {

    int count = 0;
    int ackcount = 0;
    String message = "";
    boolean abort = false;

    public MyRMIServerImpl() throws RemoteException {
        System.out.println("Creating server Object ...");
    }

    // define remote methods for clients
    public String prepare() throws RemoteException {
        String prepare = "prepare";
        return (prepare);
    }

    public String message() throws RemoteException {
        return message;
    }

    public String receive_abort() throws RemoteException, InterruptedException {
        int num = 3 - count;
        receive_ready(num);
        abort = true;
        String x = abort();
        return x;
    }

    public String commit() throws RemoteException {
        return "commit";
    }

    public String abort() throws RemoteException {
        if (abort == false) {
            System.out.println("abort");
        }
        return "abort";
    }

    public void receive_ready(int num) throws RemoteException, InterruptedException {
        count = count + num;
        if (num == 1 && abort == false) {
            System.out.println(count + " ready received");
        } else if (num == 1 && abort == true) {
            count = 3;
        }
        if (count == 2) {
            message = commit();
        } else if (count == 3) {
            message = abort();
        } else {
            TimeUnit.SECONDS.sleep(5);
            receive_ready(0);
        }

    }

    public void ack() throws RemoteException {
        ackcount++;
        System.out.println(ackcount + " " + message + " ack received");
        if(count==2){
            System.out.println("Acknowledgements received");
        }
        if(ackcount==2 && message.equalsIgnoreCase("abort")){
            System.out.println("Abort ACK received");
        }
    }

    public static void main(String args[]) {
        try {
            // create a server object
            MyRMIServerImpl myserver = new MyRMIServerImpl();

            // bind/register the server object (RMI registry)
            // Naming.rebind("rmi://localhost:5001/Chinmay",myserver);
            LocateRegistry.createRegistry(3000);
            Naming.rebind("rmi://localhost:3000/a", myserver);
            System.out.println("Server Ready... \nprepare message sent");

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }
}