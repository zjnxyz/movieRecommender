package zjn.model;

import java.util.HashMap;
import java.util.Map;


public class MovieList {
	
	public static final String MOVIE = "movie";
	public static final String VALUE = "score";
	
private Map<Movie, Float> movies = new HashMap<Movie, Float>();
	
	public void add(Movie m, Float f){
		movies.put(m, f);
	}
	
	public Float get(Movie m){
		return movies.get(m);
	}
	
	public Float remove(Movie m){
		return movies.remove(m);
	}
	
	public String toJSON(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean flag = false;
		for(Map.Entry<Movie, Float> item: movies.entrySet()){
			
			if(flag){
				sb.append(", ");
			} else {
				flag = true;
			}
			sb.append("{\"" + MOVIE + "\":" + item.getKey().toJSON() + ", ");
			sb.append("\"" + VALUE + "\":" + item.getValue() + "}");
			
		}
		sb.append("]");
		return sb.toString();
	}

}
