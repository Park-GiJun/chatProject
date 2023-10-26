package chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ChatappDao {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##chatapp";
	String password = "chatapp1234";
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public ArrayList<ChatappVo> list(String user_id) {
		ArrayList<ChatappVo> ar = new ArrayList<ChatappVo>();
		
		try {
			connDB();
			
			String query = "SELECT * FROM Login ";
			if(user_id != null) {
				query += "where user_id ";
			}
			System.out.println("SQL : " + query);
			
			rs = stmt.executeQuery(query);
			rs.last();
			
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected....");
			} else {
				System.out.println(rs.getRow() + " rows selected...");
				rs.previous();
				
				while (rs.next()) {
					String user_pwd = rs.getString("user_pwd");
					
					ChatappVo data = new ChatappVo(user_id, user_pwd);
					ar.add(data);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ar;
	}
	
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
