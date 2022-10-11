import java.sql.*;
import javax.sql.*;

public class jdbc_test {
	static Connection con = null;
	static Statement state = null;
	static ResultSet rs = null;
	// Class.forName("com.mysql.cj.jdbc.Driver");
	static String url = "bqvq6lck6dm10vfsutvk-mysql.services.clever-cloud.com";
	static String username = "ug7fbb0xexnrfoza";
	static String password = "qhXvGeJmuvKQLikmmkkJ";
	public static void main(String[] args) {

		
		try {
		    con = DriverManager.getConnection(url,username,password);
		    System.out.println("YAAYYY");
		    state = con.createStatement();
		    rs = state.executeQuery("SELECT * FROM citybank");
		    while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
			con.close();  
			// Do something with the Connection
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}

	public String insert(int rno, String name, float cgpa) throws Exception {
		state = con.createStatement();
		rs = state.executeQuery("INSERT INTO STUDENT VALUES("+rno+", "+name+", "+cgpa+")");
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));  
        return null;
    }

    public String update(int rno, String name, float cgpa) throws Exception {
        state = con.createStatement();
		rs = state.executeQuery("UPDATE STUDENT SET RNO = "+rno+", "+"NAME = "+name+", "+"CGPA = "+cgpa);
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));  
        return null;
    }

    public String delete(int rno) throws Exception {
		state = con.createStatement();
		rs = state.executeQuery("DELETE FROM STUDENT WHERE RNO = "+rno);
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));  
        return null;
    }

    public String display() throws Exception {
        state = con.createStatement();
		rs = state.executeQuery("SELECT * FROM STUDENT");
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));  
        return null;
    }

    public String selectwithrno(int rno) throws Exception {
        state = con.createStatement();
		rs = state.executeQuery("SELECT * FROM STUDENT WHERE RNO = "+rno);
		while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));  
        return null;
    }
}
