import java.rmi.*;
import java.util.ArrayList;

public interface MyRMIInterface extends Remote {
    // declare functions here
    public String insert(int rno, String name, float cgpa) throws Exception;
    public String update(int rno, String name, float cgpa) throws Exception;
    public String delete(int rno) throws Exception;
    public ArrayList<String> display() throws Exception;
    public ArrayList<String> selectwithrno(String rno) throws Exception;

}