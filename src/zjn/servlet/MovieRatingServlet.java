package zjn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zjn.dao.RatingDao;
import zjn.model.Rating;
import zjn.model.User;

public class MovieRatingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userIDSting = request.getParameter("userID");
		String movieIDString = request.getParameter("movieID");
		String scoreString = request.getParameter("score");
		String message = "";
		int userID = 0;
		int movieID = 0;
		int score = 0;
		
		Rating rate = new Rating();
		if(userIDSting != null && !"".equals(userIDSting)){
			userID = Integer.parseInt(userIDSting);	
		}else{
			throw new ServletException("传入的用户id为空");
		}
		
		if(movieIDString != null && !"".equals(movieIDString)){
			movieID = Integer.parseInt(movieIDString);	
		}else{
			throw new ServletException("传入的电影id为空");
		}
		
		if(scoreString != null && !"".equals(scoreString)){
			score = Integer.parseInt(scoreString);	
		}else{
			throw new ServletException("传入的分数为空");
		}
		
		if(userID >0 && movieID>0 && score>0f){
			rate.setMovie_id(movieID);
			rate.setUser_id(userID);
			rate.setRating(score);
		}else{
			message = "传入参数有问题";
			writeJSON(response,message);
		}
		
		if(rate != null){
			RatingDao.insertRating(rate);
			message = "你的评价已保存到数据库";
			writeJSON(response,message);
		}

	}
	
	private static void writeJSON(HttpServletResponse response, String message) throws IOException {
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
	    PrintWriter writer = response.getWriter();
	   // writer.print(user.toJSON());
	    String str="{\"message\""+":\""+message+"\"}";
	    System.out.println(str);
	    writer.print(str);
	    
	    
	  }

}
