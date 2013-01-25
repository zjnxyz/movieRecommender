package zjn.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class StringUtilTest {
	
	@Test
	public void testSplitString(){
		String str="朱江楠,只是,大笨蛋,为什么,因为,就是,大,笨蛋";
	    List<String> strList =	StringUtil.splitString(str, ",");
	    for(String s:strList){
	    	System.out.println(s);
	    }
	}
    
	@Test
	public void testConnectString(){
		String str="朱江楠,只是,大笨蛋,为什么,因为,就是,大,笨蛋";
	    List<String> strList =	StringUtil.splitString(str, ",");
	    String strLine = StringUtil.connectString(strList, "--");
	    System.out.println(strLine);
	}
	
	@Test
	public void testConnectString2(){
		List<Integer> list= new ArrayList<Integer>(); 
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
	    String strLine = StringUtil.connectString(list);
	    System.out.println(strLine);
	}
	
	@Test
	public void testRandomData(){
		RandomData.randomData();
	}
	@Test
	public void testRandomDataList(){
	
		List<Integer > a =RandomData.randomDataList4();
	   for(int i=0;i<50;i++){
		   System.out.println(i+":"+a.get(i));
	   }
	}
	
	@Test
	public void testRandom5(){
	
		RandomData.radomList5();
	  
	}
}
