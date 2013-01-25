package zjn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zjn.dao.MovieDao;
import zjn.model.Movie;
import zjn.model.MovieList;

public class MovieShowServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idString = request.getParameter("movieID");
		int movieID = 0;
		if(idString !=null && !"".equals(idString)){
			movieID = Integer.parseInt(idString);
		}else{
			throw new ServletException("没有获得前台的movieID！！！");
		}
		
		Movie movie = MovieDao.getMovieById(movieID);
		
		if(movie != null){
			writeJSON(response,movie);
		}else{
			throw new ServletException("该电影不存在！！！");
		}
	}
	
	private static void writeJSON(HttpServletResponse response, Movie movie)
	 throws IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();
		writer.print(movie.toJSON());
     }

}
