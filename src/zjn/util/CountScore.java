package zjn.util;

import java.util.ArrayList;
import java.util.List;

import zjn.dao.MovieDao;
import zjn.dao.RatingDao;
import zjn.dao.UserDao;
import zjn.model.*;

public class CountScore {
	
   public static List<Rating> count(List<User> users ,List<Movie> movies){
	    List<Rating> ratingList = new ArrayList<Rating>();
	    Rating rate ; 
	     for(User u:users){
	    	 System.out.println(u.getName());
	    	 List<String> useType = u.getHobbyList();
	    	 for(Movie m:movies){
	    		 int count = 0;
	    		 rate = new Rating();
	    		 rate.setUser_id(u.getId());
	    		 rate.setMovie_id(m.getId());
	    		 List<String> movieType = m.getType();
	    		 for(String str:movieType){
	    			 if(useType.contains(str)){
	    				 count++;
	    			 }
	    		 }
	    		 
	    		 if(count == 3){
	    			 rate.setRating(5);
	    		 }else if(count == 2){
	    			 rate.setRating(4);
	    		 }else if(count == 1){
	    			 rate.setRating(2);
	    		 }else{
	    			 rate.setRating(1);
	    		 }
	    		 
	    		 ratingList.add(rate);
	    	 }
	     }
	   
	     return ratingList;
	   
	    }
   
   public static List<Rating> count3(List<User> users ){
	    List<Rating> ratingList = new ArrayList<Rating>();
	    Rating rate ; 
	    System.out.println(users.size());
	     for(User u:users){
	    	 System.out.println(u.getName());
	    	 List<Movie> movies = MovieDao.getMovies(RandomData.randomDataList4());
	    	 List<String> useType = u.getHobbyList();
	    	 for(Movie m:movies){
	    		 int count = 0;
	    		 rate = new Rating();
	    		 rate.setUser_id(u.getId());
	    		 rate.setMovie_id(m.getId());
	    		 List<String> movieType = m.getType();
	    		 for(String str:movieType){
	    			 if(useType.contains(str)){
	    				 count++;
	    			 }
	    		 }
	    		 
	    		 if(count == 3){
	    			 rate.setRating(5);
	    		 }else if(count == 2){
	    			 rate.setRating(4);
	    		 }else if(count == 1){
	    			 rate.setRating(2);
	    		 }else{
	    			 rate.setRating(1);
	    		 }
	    		 
	    		 ratingList.add(rate);
	    	 }
	     }
	   System.out.println(ratingList.size());
	     return ratingList;
	   
	    }
   
   public static List<Rating> count2(List<User> users ,List<Movie> movies){
	    List<Rating> ratingList = new ArrayList<Rating>();
	    Rating rate ; 
	     for(int i=15;i<30;i++){
	    	 System.out.println(users.get(i).getName());
	    	 List<String> useType = users.get(i).getHobbyList();
	    	 for(Movie m:movies){
	    		 int count = 0;
	    		 rate = new Rating();
	    		 rate.setUser_id(users.get(i).getId());
	    		 rate.setMovie_id(m.getId());
	    		 List<String> movieType = m.getType();
	    		 for(String str:movieType){
	    			 if(useType.contains(str)){
	    				 count++;
	    			 }
	    		 }
	    		 
	    		 if(count == 3){
	    			 rate.setRating(5);
	    		 }else if(count == 2){
	    			 rate.setRating(4);
	    		 }else if(count == 1){
	    			 rate.setRating(2);
	    		 }else{
	    			 rate.setRating(1);
	    		 }
	    		 
	    		 ratingList.add(rate);
	    	 }
	     }
	   
	     return ratingList;
	   
	    }
  
   
   public static List<Rating> count(User user ,List<Movie> movies){
	    List<Rating> ratingList = new ArrayList<Rating>();
	    Rating rate ; 
	    	 List<String> useType = user.getHobbyList();
	    	 for(Movie m:movies){
	    		 int count = 0;
	    		 rate = new Rating();
	    		 rate.setUser_id(user.getId());
	    		 rate.setMovie_id(m.getId());
	    		 List<String> movieType = m.getType();
	    		 for(String str:movieType){
	    			 if(useType.contains(str)){
	    				 count++;
	    			 }
	    		 }
	    		 
	    		 if(count == 3){
	    			 rate.setRating(5);
	    		 }else if(count == 2){
	    			 rate.setRating(4);
	    		 }else if(count == 1){
	    			 rate.setRating(2);
	    		 }else{
	    			 rate.setRating(1);
	    		 }
	    		 
	    		 ratingList.add(rate);
	    	 }
	     
	   
	     return ratingList;
	   
	    }
   
   public static List<Rating> count2(User user ,List<Movie> movies){
	    List<Rating> ratingList = new ArrayList<Rating>();
	    Rating rate ; 
	    	 List<String> useType = user.getHobbyList();
	    	 for(Movie m:movies){
	    		 int count = 0;
	    		 rate = new Rating();
	    		 rate.setUser_id(user.getId());
	    		 rate.setMovie_id(m.getId());
	    		 List<String> movieType = m.getType();
	    		 for(String str:movieType){
	    			 if(useType.contains(str)){
	    				 count++;
	    			 }
	    		 }
	    		 
	    		 if(count == 3){
	    			 rate.setRating(5);
	    		 }else if(count == 2){
	    			 rate.setRating(4);
	    		 }else if(count == 1){
	    			 rate.setRating(2);
	    		 }else{
	    			 rate.setRating(1);
	    		 }
	    		 
	    		 ratingList.add(rate);
	    	 }
	     
	   
	     return ratingList;
	   
	    }
   
   public static List<Prefile> countPrefile(List<User> users,List<Movie> movies){
	   List<Prefile> prefiles = new ArrayList<Prefile>();
	   
	   for(Movie m:movies){
		   List<Rating> rates = RatingDao.getRatingByMovieID(m.getId());
		   int countWomen = 0;
		   int countMen = 0;
		   int num=0;
		   Prefile prefile;
		   for(Rating r:rates){
			   if(r.getRating()>3&&"m".equals(UserDao.getUserByID(r.getUser_id()).getSex())){
				      countMen++;
			   }else if(r.getRating()>3&&"w".equals(UserDao.getUserByID(r.getUser_id()).getSex())){
				      countWomen++;
			   }else{
				   num++;   
			   }
			   prefile = new Prefile();
			   if(countMen>countWomen){
				   prefile.setMovieID(m.getId());
				   prefile.setSex("m");
			   }else{
				   prefile.setMovieID(m.getId());
				   prefile.setSex("w");
			   }
		   }
		   
		   
	   } 
	   return prefiles;
   }

}
