package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {
	public Connection c;
	public Statement s;
	public static String dbName = "ims";
	public static String dbUser = "root";
	public static String dbPW = "oussama";
	
	public Conn() {
    	try {	
    		this.c = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, dbUser, dbPW);
    		s = c.createStatement();
    	} catch(Exception E) {
    		E.printStackTrace();
    	}
    }
	
	public void close() {
		try {
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
