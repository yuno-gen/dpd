import java.rmi.*;
import java.net.MalformedURLException;
import java.util.*;

public class MyRMIClient 
{
    public static void main(String args[]) 
    {
        int n=0;		
        String id="";
        Scanner scanner=new Scanner(System.in);

        try
        {
            //binding the client with server using rmi interface
            MyRMIInterface obj =(MyRMIInterface)Naming.lookup("Sayandeep");
            System.out.print("Enter the string: ");
            String input=scanner.nextLine();

            //invoking remote method
            String rev = obj.reverse(input);
            System.out.println("Reverse string: "+rev);
        }
        catch(Exception ex) 
        {
            System.out.println("Error " +ex);
        }	
    }   
}