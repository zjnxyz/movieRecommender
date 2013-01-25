package zjn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zjn.model.User;
import zjn.util.DBUtil;
import zjn.util.StringUtil;



public class UserDao {
	
	public final static String TABLE_NAME = "users";
	public final static String ID_COLUMN = "id";
	public final static String NAME_COLUMN = "name";
	public final static String SEX_COLUMN = "sex";
	public final static String HOBBY_COLUMN = "hobby";
	
public static void insertUser(User user){
	
	Connection conn = DBUtil.getConnection();
	PreparedStatement ps = null;
	String sql = "insert into "
			+ TABLE_NAME + " ( "
			+ ID_COLUMN + ", "
			+ NAME_COLUMN + ", "
			+ SEX_COLUMN +", "
			+HOBBY_COLUMN
			+ ") values (?, ?, ?, ?)";
	try {
		conn.setAutoCommit(false);

		ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSex());
			ps.setString(4, StringUtil.connectString(user.getHobbyList(), ", "));
			ps.addBatch();
		    ps.executeBatch();
		    conn.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	}


   public static int getCountUser(){
	   int count=0;
	    Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String sql="SELECT COUNT(*) as totalcount FROM "+TABLE_NAME;
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("totalcount");
				System.out.println("count:"+count);
			}
			
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   return count;
   }
	
	public static void insertUsers(List<User> users){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into "
				+ TABLE_NAME + " ( "
				+ ID_COLUMN + ", "
				+ NAME_COLUMN + ", "
				+ SEX_COLUMN +", "
				+HOBBY_COLUMN
				+ ") values (?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			for (User user : users) {
				ps.setInt(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getSex());
				ps.setString(4, StringUtil.connectString(user.getHobbyList(), ", "));
				ps.addBatch();
			}
			
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
public static User getUserByID(int userID){
		
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " =  " + userID + " ";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//conn = DBUtil.getConnectionFromDataSource();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = constructUserFromResultSet(rs);
				return user;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

public static List<User> getAllUser(){
	
	String sql = "SELECT * FROM " + TABLE_NAME ;
	List<User> users = new ArrayList<User>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		//conn = DBUtil.getConnectionFromDataSource();
		conn = DBUtil.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
//		conn = DBUtil.getConnection();
//		pstmt = conn.prepareStatement(sql);
//		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			User user = constructUserFromResultSet(rs);
			users.add(user);
		}	
		return users;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	return null;
}

   public static void deleteAll(){
	   String sql="DELETE FROM "+TABLE_NAME;
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   try {
		conn = DBUtil.getConnectionFromDataSource();
		pstmt = conn.prepareStatement(sql);
		pstmt.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }


private static User constructUserFromResultSet(ResultSet rs) {
	try {
		User user = new User();
		user.setId(rs.getInt(ID_COLUMN));
		user.setName(rs.getString(NAME_COLUMN));
		user.setSex(rs.getString(SEX_COLUMN));
		user.setHobbyList(Arrays.asList(rs.getString(HOBBY_COLUMN).split(", ")));
		return user;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
