package zjn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zjn.dao.UserDao;
import zjn.model.User;

public class UserLoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           String idString = request.getParameter("id");
           System.out.println(idString);
           int id=0 ;
           if(idString!=null){
        	  id = Integer.parseInt(idString);
           }
           
           String username = request.getParameter("username");
           System.out.println(id+":"+username);
           User user = null;
           if(id !=0&&username != null){
        	   user = UserDao.getUserByID(id);
        	   System.out.println(user.getName());
        	   if(username.equals(user.getName())){
        		   writeJSON(response,user);
        	   }else{
        		   throw new ServletException("user was not specified");
        	   }
           }else{
        	   throw new ServletException("user was not specified");
           }
		
	}
	
	private static void writeJSON(HttpServletResponse response, User user) throws IOException {
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
	    PrintWriter writer = response.getWriter();
	   // writer.print(user.toJSON());
	    String str="";
	    str = "\"id\""+":\""+user.getId()+"\",\"name\""+":\""+user.getName()+"\",\"sex\""+":\""+user.getSex()+"\"";
	    System.out.println(str);
	    
	    writer.print("{"+str+"}");
	    
	    
	  }

}
