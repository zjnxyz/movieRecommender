package zjn.model;

public class MovieSimilarity {
	
	private int movieID1;
	private int movieID2;
	private double similarity;
	
	public MovieSimilarity(int movieID1, int movieID2, double similarity){
		this.movieID1 = movieID1;
		this.movieID2 = movieID2;
		this.similarity = similarity;
	}
	public MovieSimilarity(){
		
	}
	public int getMovieID1() {
		return movieID1;
	}
	public void setMovieID1(int movieID1) {
		this.movieID1 = movieID1;
	}
	public int getMovieID2() {
		return movieID2;
	}
	public void setMovieID2(int movieID2) {
		this.movieID2 = movieID2;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
