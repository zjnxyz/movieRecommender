package zjn.init;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import zjn.dao.MovieDao;
import zjn.dao.PrefileDao;
import zjn.dao.RatingDao;
import zjn.dao.UserDao;
import zjn.model.Movie;
import zjn.model.Prefile;
import zjn.model.Rating;
import zjn.model.User;
import zjn.util.CountScore;
import zjn.util.RandomData;


public class DataTest {
	
  private static String [] username = {"刘若英","刘若海","朱海英","朱江楠","黎奉兵","罗超群","魏巧玲",
	                                    "郑婷婷","东东","嘿嘿","太多","我劝他","杨桃","热天","挺累",
	                                    "朱婷婷","很东东","王嘿嘿","谢太多","你劝他","杨桃红","大热天","我挺累",
	                                    "刘英","刘海","朱英","江楠","奉兵","超群","魏玲"
                                       };
  private static String [] sex = {"m","w"};
  
  private static String [] type = {"恐怖","动作","喜剧","犯罪","奇幻","冒险","战争",
	                               "灾难","爱情","科幻","武侠","家庭","记录","剧情","高清",
                                     };
  private static String [] movieName = {"雨夜花","民最大党","限时追捕","美国战争","黑衣人","踢出一个未来","物小王子","怪霹雳娇娃","谈谈情跳跳舞","热爱岛",
	                                    "第七期","基因时代","食人鱼","花木兰","传染病","兽国语","怪暂告安全","怪暂告","告安全","霹雳娇娃",
	                                    "黑色闪电","闪电","色闪","镜子面具","子面具","镜面","面具","护宝奇兵","奇兵","护宝",
	                                    "宝奇","护兵","宝兵","风云","雄霸天下","天下","雄霸","云之雄霸","","",
                                   "雨夜","民最","限时","美国战争2","黑衣","未来","小王子","怪霹雳","谈跳舞","热岛",
                                   "酒泉卫星","发射","基地","将迎","来规","模空","前的","航天","盛宴","神舟九号",
                                   "飞船","将在此","起飞","并首次","通过","人工手","动对接","在轨运行","天宫一号","目标飞行器"};
  @Test
  public void testAddUser(){
	  User user ;
	  List<User> userList= new ArrayList<User>();
	  List<String> hobbyList; 
	  int[] b = RandomData.randomData();
	  int [] a = RandomData.randomDataList();
	  for(int i=1;i<16;i++){
		  hobbyList = new ArrayList<String>();
		  user = new User();
		  user.setId(i+15);
		  user.setName(username[i+14]);
		  String s1 =  type[a[i-1]];
		  String s2 = type[a[i+14]];
		  String s3 = type[a[i+29]];
		  System.out.println(s1);
		  System.out.println(s2);
		  System.out.println(s3);
		  System.out.println("--------");
		  hobbyList.add(s1);
		  hobbyList.add(s2);
		  hobbyList.add(s3);
		 
		  user.setSex(sex[b[i-1]]);
		  user.setHobbyList(hobbyList);
		  userList.add(user);
		  //hobbyList.clear();
	  }
	  
	  UserDao.insertUsers(userList);
	  
  }
  
  @Test
 public void testAddMovies(){
	  
	  Movie movie;
	  List<Movie> movieList= new ArrayList<Movie>();
	  List<String> TypeList;
	  int [] a = RandomData.randomDataList2();
	  for(int i = 1;i<movieName.length+1;i++){
		  TypeList = new ArrayList<String>();
		  movie = new Movie();
		  movie.setId(i);
		  movie.setName(movieName[i-1]);
		  movie.setYear("1989-7-1");
		  String s1 = type[a[i-1]];
		  String s2 = type[a[i+49]];
		  String s3 = type[a[i+99]];
		  TypeList.add(s1);
		  TypeList.add(s2);
		  TypeList.add(s3);
		  movie.setType(TypeList);
		  movieList.add(movie);  
	  }
	  
	  MovieDao.insertMovies(movieList);
 }
  
  @Test
  public void testAddMovies2(){
 	  
 	  Movie movie;
 	  List<Movie> movieList= new ArrayList<Movie>();
 	  List<String> TypeList;
 	  int [] a = RandomData.randomDataList3();
 	  for(int i = 1;i<21;i++){
 		  TypeList = new ArrayList<String>();
 		  movie = new Movie();
 		  movie.setId(i+50);
 		  movie.setName(movieName[i+49]);
 		  movie.setYear("1989-7-1");
 		  String s1 = type[a[i-1]];
 		  String s2 = type[a[i+19]];
 		  String s3 = type[a[i+39]];
 		  TypeList.add(s1);
 		  TypeList.add(s2);
 		  TypeList.add(s3);
 		  movie.setType(TypeList);
 		  movieList.add(movie);  
 	  }
 	  
 	  MovieDao.insertMovies(movieList);
  }
  
  @Test
  public void testAddRating(){
	  List<Movie> movieList = MovieDao.getAllMovies();
	  List<User> userList = UserDao.getAllUser();
	  List<Rating> rateList=CountScore.count(userList, movieList);
	  RatingDao.insertRatings(rateList);
  }
  
  @Test
  public void testAddRating2(){
	  
	  List<User> userList = UserDao.getAllUser();
	  List<Rating> rateList = new ArrayList<Rating>();
	  System.out.println(userList.size());
	  for(int i=0;i<30;i++){
		 List<Movie> movieList = MovieDao.getMovies(RandomData.randomDataList4());
		 rateList=CountScore.count2(userList.get(i), movieList);
	  }
	 
	  RatingDao.insertRatings(rateList);
  }
  
  @Test
  public void testAddRating3(){
	  List<User> userList = UserDao.getAllUser();
	  System.out.println(userList.size());
	  List<Rating> rateList=CountScore.count3(userList);
	  //RatingDao.insertRatings(rateList);
  }
  
  @Test
  public void testAddPrifile(){
	  List<Movie> movieList = MovieDao.getMovies(RandomData.randomDataList4());
	  List<User> userList = UserDao.getAllUser();
	  List<Prefile> prefiles = CountScore.countPrefile(userList, movieList);
	  for(Prefile p:prefiles){
		  System.out.println(p.getSex());
	  }
	  PrefileDao.insertPrefile(prefiles);
  }
  


}
