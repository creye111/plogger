package pptest;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			//Connect to existing SQLite DB
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
			return 1;
		}else {
			//Create new SQLite DB
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
			return 1;
		}
		return 0;
	}
	public Connection connect() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(path);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	/**
	 * Method to connect to DB and insert a row into the session table.
	 * @param date
	 * @param time
	 * @param duration
	 * @return
	 */
	public int insertSession(String date, String time, int duration) {
		String qry="INSERT INTO session(date, time, duration) VALUES(?,?,?)";
		try{
			Connection conn = DriverManager.getConnection(path);
			PreparedStatement ps = conn.prepareStatement(qry);
			ps.setString(1,date);
			ps.setString(2, time);
			ps.setInt(3, duration);
			ps.executeUpdate();
			conn.close();
		}catch(SQLException e) {
			e.getMessage();
			return 1;
		}
		return 0;
	}
	/**
	 * Prints all session Table contents
	 * @return
	 */
	public void printSessionTableContents() {
		String qry = "SELECT session_id, date, time, duration FROM session";
		ResultSet copy=null;
		try(Connection c = this.connect();
			Statement selectStatement = c.createStatement();
			ResultSet rs = selectStatement.executeQuery(qry)){
			while (rs.next()) {
				System.out.println(rs.getString("date")+" | "+ rs.getInt("duration"));
			}
			c.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		//return copy;
	}
	
}
