package zjn.model;

public class Prefile {
	
	public static final String MOVIE_ID = "movieID";
	public static final String SEX = "sex";
	
	private int movieID;
	private String sex;
    
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String toJSON(){
		StringBuilder sb = new StringBuilder();
		sb.append("{'"+MOVIE_ID+"':'"+movieID+"',");
		sb.append("{'"+SEX+"':'"+sex+"'}");
		return sb.toString();
	}

}
