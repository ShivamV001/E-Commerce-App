package com.general.Factory;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
private static Connection connection;
static {
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "user123");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
public static Connection getConnection() {
	return connection;
}
public static void cleanUp() {
	try {
		connection.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}
}