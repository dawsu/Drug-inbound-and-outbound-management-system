package link;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Link {
	 public static  Connection getConnection() {
		 Connection con=null;
	     try {
	    	 Class.forName("com.mysql.cj.jdbc.Driver");
	         con=DriverManager.getConnection(VariableTable.url,VariableTable.user,VariableTable.password);
	     } catch (Exception e) {	        	
	         e.printStackTrace();
	     }
	     return con;
	 }
	 public static void close(Connection con) {
		 if(con!=null)
	     try {
	         con.close();
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }       
	 }
}
