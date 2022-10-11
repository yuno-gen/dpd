import java.rmi.*;
import java.util.*;

public class MyRMIClient {
    public static void main(String args[]) {
        int n = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                MyRMIInterface obj = (MyRMIInterface) Naming.lookup("rmi://localhost:3000/a");

                // call remote methods
                String prepare = obj.prepare();
                System.out.println(prepare + " received");
                String message = "";
                System.out.println("0 is abort and 1 is ready");
                n = scanner.nextInt();
                if (n == 1) {
                    obj.receive_ready(1);
                    message = obj.message();
                } else {
                    message = obj.receive_abort();
                }
                System.out.println(message);
                obj.ack();
            } catch (Exception ex) {
                System.out.println("Error " + ex);
            }
        }
    }
}