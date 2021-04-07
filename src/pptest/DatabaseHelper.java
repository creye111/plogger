package pptest;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	public String path = "jdbc:sqlite:"+System.getProperty("user.dir")+"\\res\\pplogger.db";
	private String SESSION_TABLE_NAME;
	private String SESSION_COL_0="session_id";
	private String SESSION_COL_1="date";
	private String SESSION_COL_2="time";
	private String SESSION_COL_3="duration";
	
	DatabaseHelper(){
		
	}
	DatabaseHelper(String dbFileName){
		path = "jdbc:sqlite:"+System.getProperty("user.dir")+"\\res\\"+dbFileName;
	}
	
	public int createConnection() {
		File dbFile = new File(System.getProperty("user.dir")+"/res/pplogger.db");
		if(dbFile.exists()) {
			Connection conn = null;
			//TODO: Connect to existing SQLite DB
			try {
				conn = DriverManager.getConnection(path);
				System.out.println("DB Exists! Connection established!");
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
						System.out.println("CONN CLOSED!");
					}
				}catch(SQLException e) {
					e.getMessage();
				}
			}
			
		}else {
			//TODO: Create new SQLite DB
			try(Connection conn = DriverManager.getConnection(path)){
				if(conn!= null) {
					DatabaseMetaData meta = conn.getMetaData();
					System.out.println("The driver name is " + meta.getDriverName());
					System.out.println("A new db is here");
					createSessionTable();
				}
			}catch(SQLException e) {
				e.getMessage();
			}
			
			
			
			System.out.println(path);
			
		}
		return 0;
	}
	
	private int createSessionTable() {
		String query = "CREATE TABLE IF NOT EXISTS session(\n"+
						"	session_id INTEGER PRIMARY KEY,\n"+
						"	date TEXT,\n"+
						"	time TEXT,\n"+
						"	duration INTEGER\n"+
						");";
		try(Connection conn = DriverManager.getConnection(path)){
			Statement s = conn.createStatement();
			s.execute(query);
			if(conn != null)
				conn.close();
		}catch(SQLException e) {
			e.getMessage();
		}
		return 0;
	}
	
	private int insertSession(String date, String time, int duration) {
		String qry="INSERT INTO session ( "+"("+SESSION_COL_1+","+SESSION_COL_2+","+SESSION_COL_3+")\n"+
											"VALUES("+","+","+",";
		try(Connection conn = DriverManager.getConnection(path)){
			Statement s = conn.createStatement();
			s.execute(qry);
		}catch(SQLException e) {
			e.getMessage();
		}
		return 0;
	}
	
}
