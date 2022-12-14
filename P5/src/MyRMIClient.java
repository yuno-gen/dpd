import java.rmi.*;
import java.util.*;

public class MyRMIClient {
    public static void main(String args[]) {
        int n = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            try {

                // call remote methods
                // String prepare = obj.prepare();
                // System.out.println(prepare + " received");
                // String message = "";
                // System.out.println("0 is abort and 1 is ready");
                // n = scanner.nextInt();
                // if (n == 1) {
                //     obj.receive_ready(1);
                //     message = obj.message();
                // } else {
                //     message = obj.receive_abort();
                // }
                // System.out.println(message);
                // obj.ack();
                int ch = 0;
                while(ch!=6) {

                    System.out.println("1. Insert\n2. Update\n3. Delete\n4. Select\n5. Select with Roll No.\n6. Exit\n");
                    Scanner sc = new Scanner(System.in);
                    ch = sc.nextInt();
                    int rno;
                    float cgpa;
                    String name;
                    switch (ch) {
                        case 1:
                            MyRMIInterface insert = (MyRMIInterface) Naming.lookup("rmi://localhost:4001/insert");
                            System.out.print("Enter Name: ");
                            name = sc.next();
                            System.out.print("Enter Roll No.: ");
                            rno = sc.nextInt();
                            System.out.print("Enter CGPA: ");
                            cgpa = sc.nextFloat();
//                        System.out.println(name);
//                        System.out.println(cgpa);
//                        System.out.println(rno);
                            System.out.println(insert.insert(rno, name, cgpa));
                            break;
                        case 2:
                            MyRMIInterface update = (MyRMIInterface) Naming.lookup("rmi://localhost:4001/update");
                            System.out.print("Enter Name: ");
                            name = sc.next();
                            System.out.print("Enter Roll No.: ");
                            rno = sc.nextInt();
                            System.out.print("Enter CGPA: ");
                            cgpa = sc.nextFloat();
                            System.out.println(update.update(rno, name, cgpa));
                            break;
                        case 3:
                            MyRMIInterface delete = (MyRMIInterface) Naming.lookup("rmi://localhost:4001/delete");
                            System.out.print("Enter Roll No.: ");
                            rno = sc.nextInt();
                            System.out.println(delete.delete(rno));
                            break;
                        case 4:
                            MyRMIInterface select = (MyRMIInterface) Naming.lookup("rmi://localhost:4001/select");
                            System.out.println("\n---------------");
                            for(String s: select.display()) {
                                System.out.println(s);
                            }
                            System.out.println("-----------------");
                            break;
                        case 5:
                            MyRMIInterface selectwithrno = (MyRMIInterface) Naming.lookup("rmi://localhost:4001/selectwithrno");
                            System.out.print("Enter Where condition: ");
                            name = sc.next();
                            System.out.println("\n-----------------");
                            for(String s: selectwithrno.selectwithrno(name)) {
                                System.out.println(s);
                            }
                            System.out.println("-----------------");
                            break;
                        default:
                            break;
                    }
                }

            } catch (Exception ex) {
                System.out.println("Error " + ex);
            }
        }
    }
}