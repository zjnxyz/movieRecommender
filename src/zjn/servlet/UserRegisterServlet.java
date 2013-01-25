package zjn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zjn.dao.UserDao;
import zjn.model.User;

public class UserRegisterServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rUsername = request.getParameter("rUsername");
		String rHobby = request.getParameter("rHobby");
		String sex = request.getParameter("rSex");
		System.out.println(sex);
		List<String> hobbyList;		
		String message = "";
		User user = new User();
		if(rUsername != null&&!"".equals(rUsername)){
			user.setName(rUsername);
		}else{
			message = "请输入姓名";
			writeJSON(response,null,message);
		}
		
		if(rHobby != null && !"".equals("rHobby")){
			System.out.println("到这里了hobby");
			hobbyList = new ArrayList<String>();
			for(String s:Arrays.asList(rHobby.split("，"))){
				System.out.println(s);
				hobbyList.add(s);
			}
			user.setHobbyList(hobbyList);
			
		}else{
			message = "请输入爱好，并以逗号分开";
			writeJSON(response,null,message);
		}
		if(sex != null&&!"".equals(sex)){
			user.setSex(sex);
		}else{
			user.setSex("M");
		}
		
		if(user != null){
			int userID = UserDao.getCountUser();
			user.setId(userID+1);
		}
		
		if(user.getId()!= 0){
			System.out.println("woshi id");
		   UserDao.insertUser(user);
		   User u = UserDao.getUserByID(user.getId());
		   message = "注册成功";
		   writeJSON(response,u,message);
		}else{
			message = "注册不成功。请检查信息";
			writeJSON(response,null,message);
		}
		
	}
	
	private static void writeJSON(HttpServletResponse response, User user,String message) throws IOException{
		   response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
		    response.setHeader("Cache-Control", "no-cache");
		    PrintWriter writer = response.getWriter();
		    String str = "";
		    if(user != null){
		    	str = user.toJSON();
		    	str=str.substring(0, str.length()-1);
		    	str+=",\"message\" : \""+message+"\"}";
		    	System.out.println(str);
		    	writer.print(str);
		    }else{
		    	str = "{\"message\" : \""+message+"\"}";
		    	writer.print(str);
		    }
	}

}
