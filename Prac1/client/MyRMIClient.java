import java.rmi.*;
import java.util.*;

public class MyRMIClient {
    public static void main(String args[]) {
        int n = 0;
        String id = "";
        Scanner scanner = new Scanner(System.in);

        try {
            MyRMIInterface obj = (MyRMIInterface) Naming.lookup("Ashtavinayak");
            System.out.print("Enter amount in pounds ");
            n = scanner.nextInt();

            // call remote methods
            int rs = obj.convertPoundsToRupees(n);
            System.out.println("Value of " + n + " pounds is " + rs + " rupees");
            
            System.out.print("Enter number to square: ");
            n = scanner.nextInt();
            System.out.println("Square of "+n+" : "+obj.square(n));

            System.out.print("Enter purchase amount: ");
            n = scanner.nextInt();
            System.out.println("Your discount(10%) is "+n+" : "+obj.discount(n));
            id = scanner.nextLine();
            
            System.out.print("Enter userid ");
            id = scanner.nextLine();
            System.out.println("Name : " + obj.getUserName(id));
        } catch (Exception ex) {
            System.out.println("Error " + ex);
        }
    }
}