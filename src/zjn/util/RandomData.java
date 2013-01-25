package zjn.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zjn.dao.PrefileDao;
import zjn.model.Prefile;

public class RandomData {
	
	public static int[] randomData(){
		    int [] j = new int[20];
		    Random random = new Random();
		    for(int i=0;i<20;i++){
		    	j[i] = Math.abs(random.nextInt()%2);
		    }
			for(int k = 0;k<20;k++){
				System.out.println(j[k]);
			}
			
		    return j;
	}
	
	public static int[] randomDataList(){
		    int [] a =new int [45];
		    Random random = new Random(2);
		    for(int i=0;i<45;i++){
			  int j = Math.abs(random.nextInt()%15);
			  a[i]=j;
		    }
			//System.out.println();
			return a;
	}
	
	public static int[] randomDataList2(){
	    int [] a =new int [150];
	    Random random = new Random(2);
	    for(int i=0;i<150;i++){
		  int j = Math.abs(random.nextInt()%15);
		  a[i]=j;
	    }
		//System.out.println();
		return a;
}
	
	public static int[] randomDataList3(){
	    int [] a =new int [60];
	    Random random = new Random(2);
	    for(int i=0;i<60;i++){
		  int j = Math.abs(random.nextInt()%15);
		  a[i]=j;
	    }
		//System.out.println();
		return a;
}
	
	public static List<Integer> randomDataList4(){
		 List<Integer> a = new ArrayList<Integer>();
		 Random random = new Random();
		    for(int i=0;i<50;i++){
			  int j = Math.abs(random.nextInt()%70);
			  a.add(j);
		    }
			//System.out.println();
			return a;
	}
	
	public static void radomList5(){
		List<Prefile> prefiles = new ArrayList<Prefile>();
		Prefile pre;
		 Random random = new Random();
		    for(int i=0;i<70;i++){
		    	pre = new Prefile();
			  int j = Math.abs(random.nextInt()%4); 
			  pre.setMovieID(i+1);
			  System.out.println(j);
			  
			  if(j==1){
				  pre.setSex("m");
			  }else if(j==2){
				  pre.setSex("w");
			  }else{
				  pre.setSex("u");
			  }
			  
			  prefiles.add(pre);
		    }
		    PrefileDao.insertPrefile(prefiles);
		
	}

}
