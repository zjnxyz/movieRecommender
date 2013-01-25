package zjn.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
    private static DataSource ds;
	private static String url;
	private static String dbClass;
	private static String userName;
	private static String pwd;
	
	@SuppressWarnings("unused")
	public static void createDataSource() throws Exception {
		try {
			Context context = new InitialContext();
			if (context == null) {
				throw new Exception("create context failed!");
			}
			ds = (DataSource) context.lookup("java:comp/env/jdbc/movie");
			if (ds == null) {
				Thread.sleep(2000);
				ds = (DataSource) context.lookup("java:comp/env/jdbc/movie");
				if (ds == null) {
					throw new Exception("get datasource failed!");
				}
			}
		} catch (NamingException ne) {
			throw ne;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public static DataSource getDataSource() {
		try {
			if (ds == null) {
				createDataSource();
			}
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static Connection getConnection(){
		DataSource dataSource = getDataSource();
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Connection getConnectionFromDataSource() {
		try {
			Connection con = null;
			if (ds == null) {
				createDataSource();
			}
			con = ds.getConnection();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static ResultSet execuateQuery(String sql){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnectionFromDataSource();
			//conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			return pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}
	
	/*
	static{
		try {
			InputStream is = DBUtil.class.getResourceAsStream("db.properties");
			Properties pro = new Properties();
			pro.load(is);
			dbClass = pro.getProperty("driver");
			url = pro.getProperty("url");
			userName = pro.getProperty("userName");
			pwd = pro.getProperty("pwd");
			if (dbClass == null || "".equals(dbClass))
				throw new Exception("配置文件加载不成功");
			Class.forName(dbClass);
		    } catch (Exception e) {
	              e.printStackTrace();
		  }
       }
       
	
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
		    conn=DriverManager.getConnection(url,userName,pwd);
		    conn.setAutoCommit(false);
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return conn;
	}
*/

}
