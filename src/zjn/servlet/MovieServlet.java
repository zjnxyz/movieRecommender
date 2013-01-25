package zjn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import zjn.dao.RatingDao;
import zjn.model.*;

public class MovieServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	        this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		MovieList movies;
		int id = 0;
		if(idString != null){
			id= Integer.parseInt(idString);
		}
		
		if(id != 0){
			movies = RatingDao.getMoviesByUserID(id);
			
		}else {
			throw new ServletException("Movie was not specified");
		}
		
		if (movies != null) {
			try {
				writeJSON(response, movies);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void writeJSON(HttpServletResponse response, MovieList movies)
	 throws IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();
		writer.print(movies.toJSON());
      }

}
