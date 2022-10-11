import java.rmi.*;

public interface MyRMIInterface extends Remote {
    // declare functions here
    public String insert(int rno, String name, float cgpa) throws Exception;
    public String update(int rno, String name, float cgpa) throws Exception;
    public String delete(int rno) throws Exception;
    public String display() throws Exception;
    public String selectwithrno(int rno) throws Exception;
    
}