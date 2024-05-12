package com.gl.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class BaggageUtil {
	 private static final String URL = "jdbc:postgresql://localhost:5432/Capstone_Baggage?currentSchema=baggage_tracking";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "SQL4me";
	    static AtomicInteger counter = new AtomicInteger();

	    public static Connection getConnection() throws ClassNotFoundException, SQLException {
	       Class.forName("org.postgresql.Driver");
		   Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			return conn;
	    }
	    public int generateUniqueId(String columnName, String tableName, int initialValue) {
	       
	        return 0;
	    }
	    
	    
}
