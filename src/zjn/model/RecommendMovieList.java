package zjn.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import zjn.dao.MovieDao;



public class RecommendMovieList {
	
	private List<RecommendMovie> recommendMovies = new ArrayList<RecommendMovie>();
	public RecommendMovieList(){
		
	}
	public RecommendMovieList(List<RecommendedItem> items){
		List<String> movieIDList = new ArrayList<String>();
		for (RecommendedItem item : items){
			movieIDList.add(String.valueOf(item.getItemID()));
		}
		
		Map<String, Movie> movies = MovieDao.getMovieMap(movieIDList);
		
		for (RecommendedItem item : items){
			String movieID = String.valueOf(item.getItemID());
			Movie movie = movies.get(movieID);
			if(movie != null){
				RecommendMovie rm = new RecommendMovie(movie, item.getValue());
				recommendMovies.add(rm);
			}
		}
	}

	public List<RecommendMovie> getRecommendMovies() {
		return recommendMovies;
	}

	public void setRecommendMovies(List<RecommendMovie> recommendMovies) {
		this.recommendMovies = recommendMovies;
	}
	
	public String toJSON(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean flag = false;
		for(RecommendMovie movie : recommendMovies){
			if(flag){
				sb.append(", ");
			} else {
				flag = true;
			}
			sb.append(movie.toJSON());
		}
		sb.append("]");
		return sb.toString();
	}

}
