package zjn.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtil {
	
	public static List<String> splitString(String str,String split){
		List<String> strList = new ArrayList<String>();
		String [] a = str.split(split);
		for(int i=0;i<a.length;i++){
			strList.add(a[i]);
		}
		
		return strList;
	}
	
	public static String connectString(Collection<String> stringList, String split){
		StringBuilder builder = new StringBuilder();
		boolean flag = false;
		
		for (String s : stringList){
			if( flag ) {
				builder.append( split );
			} else {
				flag = true;
			}

			builder.append(s);		
		}
		return builder.toString();
	}
	

	public static String connectString(Collection<Integer> movieIDs) {
		
		StringBuilder builder = new StringBuilder();
		boolean flag = false;
		
		for (int s : movieIDs){
			if( flag ) {
				builder.append( "," );
			} else {
				flag = true;
			}

			builder.append(s);		
		}
		return builder.toString();
	}

}
