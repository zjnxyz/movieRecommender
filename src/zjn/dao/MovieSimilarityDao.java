package zjn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;

import zjn.model.MovieSimilarity;
import zjn.util.DBUtil;


public class MovieSimilarityDao {

	public final static String TABLE_NAME = "movie_similarity";
	public final static String MOVIE_ID_1 = "movieID1";
	public final static String MOVIE_ID_2 = "movieID2";
	public final static String SIMILARITY = "similarity";
	
	public static void insertSimilarity(List<MovieSimilarity> similarities){
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into "
				+ TABLE_NAME + " ( "
				+ MOVIE_ID_1 + ", "
				+ MOVIE_ID_2 + ", "
				+ SIMILARITY 
				+ ") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			for (MovieSimilarity similarity : similarities) {
				ps.setInt(1, similarity.getMovieID1());
				ps.setInt(2, similarity.getMovieID2());
				ps.setDouble(3, similarity.getSimilarity());
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
	
	public static GenericItemSimilarity.ItemItemSimilarity constructMovieSimilarityFromResultSet(ResultSet rs){
		try {
			long movie1 = rs.getInt(MOVIE_ID_1);
			long movie2 = rs.getInt(MOVIE_ID_2);
			double rel = rs.getDouble(SIMILARITY);
			GenericItemSimilarity.ItemItemSimilarity similarity = new GenericItemSimilarity.ItemItemSimilarity(movie1, movie2, rel);
			return similarity;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<GenericItemSimilarity.ItemItemSimilarity> getAllMovieSimilarities(){
		List<GenericItemSimilarity.ItemItemSimilarity> similarities = new ArrayList<GenericItemSimilarity.ItemItemSimilarity>();
		
		String sql = "SELECT * FROM " + TABLE_NAME;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
			while (rs.next()) {
				GenericItemSimilarity.ItemItemSimilarity similarity = constructMovieSimilarityFromResultSet(rs);
				if(similarity != null){
					similarities.add(similarity);
				}
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
		return similarities;
	}
	
}
