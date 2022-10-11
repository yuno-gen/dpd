import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.concurrent.TimeUnit;

public class MyRMIServerImpl extends UnicastRemoteObject implements MyRMIInterface {

    int count = 0;
    int ack = 0;
    boolean countb = false;
    boolean ackb = false;
    int exit = 0;

    public MyRMIServerImpl() throws RemoteException {
        System.out.println("Creating server Object ...");
    }

    // define remote methods for clients
    public String prepare1() throws RemoteException {
        String prepare = "Commit Request";
        return (prepare);
    }

    public String prepare2() throws RemoteException {
        String prepare = "Prepare";
        return (prepare);
    }

    public String checkp1() throws RemoteException {
        String x = "";
        if (count == 2) {
            x = "ready";
            if (countb == false) {
                System.out.println();
                System.out.println("P2");
                System.out.println("------------------------");
                System.out.println("sending prepare message to clients");
            }
            countb = true;
        } else {
            x = "abort";
        }
        return x;
    }

    public String checkp2() throws RemoteException, InterruptedException {
        String x = "";
        if (ack == 2) {
            x = "commit";
            if (ackb == false) {
                System.out.println();
                System.out.println("P3");
                System.out.println("------------------------");
                System.out.println("sending commit message to clients");
                exit = 1;
                System.out.println("Tx committed");
            }
            // if (exit == 1) {
            // TimeUnit.SECONDS.sleep(5);
            // System.exit(0);
            // }
            ackb = true;
        } else {
            x = "abort";
        }
        return x;
    }

    public void sendreadyp1() throws RemoteException {
        count += 1;
        System.out.println(count + " ready received");
    }

    public void sendabortp1() throws RemoteException {
        count = 0;
    }

    public String abort() throws RemoteException {
        System.out.println("abort received");
        return "abort";
    }

    public void exit() throws RemoteException {
        System.exit(0);
    }

    public void ack() throws RemoteException {
        ack += 1;
        System.out.println(ack + " ack received");
    }

    public static void main(String args[]) {
        try {
            MyRMIServerImpl myserver = new MyRMIServerImpl();
            LocateRegistry.createRegistry(3000);
            Naming.rebind("rmi://localhost:3000/a", myserver);
            System.out.println("P1");
            System.out.println("------------------------");
            System.out.println("Server Ready... \nsending C_R message to clients");

        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }
}