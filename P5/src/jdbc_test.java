import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

public class jdbc_test {
    static Connection con = null;
    static Statement state = null;
    static ResultSet rs = null;
    // Class.forName("com.mysql.cj.jdbc.Driver");
    static String url = "jdbc:mysql://localhost:3306/student";
    static String username = "root";
    static String password = "";
    public void connect(){
        try {
            con = DriverManager.getConnection(url,username,password);
            System.out.println("YAAYYY");
            state = con.createStatement();
            rs = state.executeQuery("SELECT * FROM records");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
//            insert(1, "Alpha", 9.8f);
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String insertVal(int rno, String name, float cgpa) throws Exception {
        String query = String.format("INSERT INTO RECORDS (Roll_no, Name, Cgpa) VALUES(%d, %s, %f)", rno, '"'+name+'"', cgpa);
//        System.out.println(query);
//        System.out.println(query);
        state = con.createStatement();

        int res = state.executeUpdate(query);
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
        if(res==1) return "1";
        return "0";
    }

    public String updateVal(int rno, String name, float cgpa) throws Exception {
        state = con.createStatement();
        String query = String.format("UPDATE records SET NAME = %s, CGPA = %f WHERE Roll_No = %d", '"'+name+'"', cgpa, rno);
        int res = state.executeUpdate(query);
//        while(rs.next())
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
        if(res==1) return "1";
        return "0";
    }

    public String delete(int rno) throws Exception {
        state = con.createStatement();
        String query = "DELETE FROM records WHERE Roll_No = "+rno;
        int res = state.executeUpdate(query);
//        while(rs.next())
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
        if(res==1) return "1";
        return "0";
    }

    public ArrayList displayVal() throws Exception {
        state = con.createStatement();
        rs = state.executeQuery("SELECT * FROM records");
        ArrayList<String> arr = new ArrayList<>();
        while(rs.next()) {
//            System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getFloat(3));
            arr.add(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
        }
        return arr;
    }

    public ArrayList<String> selectwithrnoVal(String rno) throws Exception {
        state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String query = String.format("SELECT * FROM records WHERE Roll_no %s",rno);
//        System.out.println(query);
        rs = state.executeQuery(query);

        ArrayList<String> arr = new ArrayList<>();
//        System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
        while(rs.next()){
            arr.add(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getFloat(3));
//            System.out.println(result);
        }
        return arr;
    }
}
