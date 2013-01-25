package zjn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zjn.model.Movie;
import zjn.model.MovieList;
import zjn.model.Rating;
import zjn.util.DBUtil;



public class RatingDao {
	
	public final static String TABLE_NAME = "movie_preferences";
	public final static String USER_ID_COLUMN = "userID";
	public final static String MOVIE_ID_COLUMN = "movieID";
	public final static String RATING = "preference";
	
	public static void insertRating(Rating rating) {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + RATING 
				+ ") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
				ps.setInt(1, rating.getUser_id());
				ps.setInt(2, rating.getMovie_id());
				ps.setInt(3, rating.getRating());
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
	
	public static void insertRatings(List<Rating> ratings) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into " + TABLE_NAME + " ( " + USER_ID_COLUMN
				+ ", " + MOVIE_ID_COLUMN + ", " + RATING 
				+ ") values (?, ?, ?)";
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);

			for (Rating rating : ratings) {
				ps.setInt(1, rating.getUser_id());
				ps.setInt(2, rating.getMovie_id());
				ps.setInt(3, rating.getRating());
				
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
	
	public static List<Rating> getRatingByMovieID(int movieID){
		List<Rating> rates = new ArrayList<Rating>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + TABLE_NAME +" WHERE "+MOVIE_ID_COLUMN+"="+movieID+" ";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
			  Rating rate =	constructMoviesFromResultSet(rs,movieID);
			  rates.add(rate);
			}
			return rates;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static MovieList getMoviesByUserID(int userID) {

		String sql = "SELECT * FROM " + TABLE_NAME + " mp, "
				+ MovieDao.TABLE_NAME + " m" + " WHERE " + "mp."
				+ MOVIE_ID_COLUMN + " = m." + MovieDao.ID_COLUMN + " AND "
				+ "mp." + USER_ID_COLUMN + " =  " + userID + " ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieList movies = new MovieList();
		try {
			conn = DBUtil.getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				constructMoviesFromResultSet(rs, movies);
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
		return movies;
	}

	
	private static void constructMoviesFromResultSet(ResultSet rs,
			MovieList movies) {
		try {
			Movie movie = new Movie();
			movie.setId(rs.getInt(MovieDao.ID_COLUMN));
			movie.setName(rs.getString(MovieDao.NAME_COLUMN));
			String type = rs.getString(MovieDao.TYPE_COLUMN);
			if(type != null){
				movie.setType(Arrays.asList(type.split(", ")));
			}
			Float score = rs.getFloat(RATING);
			movies.add(movie, score);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Rating constructMoviesFromResultSet(ResultSet rs,int movieID) {
		try {
			Rating rate = new Rating();
			rate.setMovie_id(movieID);
			rate.setUser_id(rs.getInt(USER_ID_COLUMN));
			rate.setRating(rs.getInt(RATING));
			return rate;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
