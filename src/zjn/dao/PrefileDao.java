package zjn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import zjn.model.Prefile;
import zjn.util.DBUtil;

public class PrefileDao {
	
	public static final String TABLE_NAME = "prefile";
	public static final String MOVIE_ID = "movieID";
	public static final String SEX = "sex";
	
	public static void insertPrefile(Prefile prefile ){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		
		String sql="insert into "
			+TABLE_NAME+" ( "
			+MOVIE_ID+", "
			+SEX+") value(?,?)";
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, prefile.getMovieID());
			ps.setString(2, prefile.getSex());
			ps.executeBatch();
			conn.commit();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}
	
	public static void insertPrefile(List<Prefile> prefiles ){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		
		String sql="insert into "
			+TABLE_NAME+" ( "
			+MOVIE_ID+", "
			+SEX+") value(?,?)";
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for(Prefile prefile:prefiles){
			  ps.setInt(1, prefile.getMovieID());
			  ps.setString(2, prefile.getSex());
			  ps.addBatch();
			}
			ps.executeBatch();
			
			conn.commit();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
	}
	
	public static List<Prefile> getAllPrefileByMovieID(int movieID){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Prefile> prefiles = new ArrayList<Prefile>();
		String sql = "SELECT * FROM " + TABLE_NAME +" WHERE "+MOVIE_ID+"="+movieID;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Prefile prefile = constructPrefileFromResultSet(rs);
				if(prefile != null){
					prefiles.add(prefile);
				}
			}
			
			return prefiles;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	
	public static List<Prefile> getAllPrefile(){
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Prefile> prefiles = new ArrayList<Prefile>();
		String sql = "SELECT * FROM " + TABLE_NAME;
		
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Prefile prefile = constructPrefileFromResultSet(rs);
				if(prefile != null){
					prefiles.add(prefile);
				}
			}
			
			return prefiles;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	public static Prefile constructPrefileFromResultSet(ResultSet rs){
		Prefile prefile = new Prefile();
		try {
			prefile.setMovieID(rs.getInt(MOVIE_ID));
			prefile.setSex(rs.getString(SEX));
			return prefile;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prefile;
		
	}
}
