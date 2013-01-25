package zjn.model;


public class RecommendMovie {
	
	private static final String MOVIE = "movie";
	private static final String VALUE = "score";
	
	private Movie movie;
	private float value;
	
	public RecommendMovie(Movie movie, float value){
		this.movie = movie;
		this.value = value;
	}
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Movie:\t" + movie.toString() + "\t");
		sb.append("Score:\t" + value);
		return sb.toString();
	}
	
	public String toJSON(){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"" + MOVIE + "\":" + movie.toJSON() + ",");
		sb.append("\"" + VALUE + "\":" + value + "}");
		return sb.toString();
	}

}
