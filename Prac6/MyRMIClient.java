import java.rmi.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MyRMIClient {
    public static void main(String args[]) {
        int n = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                MyRMIInterface obj = (MyRMIInterface) Naming.lookup("rmi://localhost:3000/a");
                String prepare1 = obj.prepare1();
                System.out.println(prepare1 + " received from server");
                String message = "";
                System.out.println("0 is abort and 1 is ready");
                n = scanner.nextInt();
                if (n == 1) {
                    obj.sendreadyp1();
                } else {
                    obj.sendabortp1();
                }

                TimeUnit.SECONDS.sleep(6);

                String check = obj.checkp1();
                if (check.equals("ready")) {
                    String prepare2 = obj.prepare2();
                    System.out.println(prepare2 + " received from server");
                    System.out.println("ACK sent to server");
                    obj.ack();

                    TimeUnit.SECONDS.sleep(2);

                    String check2 = obj.checkp2();
                    if (check2.equals("commit")) {
                        System.out.println("Received Commit");
                        System.out.println("commited");
                    } else {
                        message = obj.abort();
                        System.out.println(message + " received from server");
                        System.out.println("Aborting Tx");
                        obj.exit();
                        System.exit(0);

                    }

                } else {
                    message = obj.abort();
                    System.out.println(message + " received from server");
                    System.out.println("Aborting Tx");
                    System.exit(0);
                }

            } catch (Exception ex) {
                System.out.println("Error " + ex);
            }
        }
    }
}
